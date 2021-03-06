package edu.wpi.cs.calliope.snippetsystem.db;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import edu.wpi.cs.calliope.snippetsystem.model.SnippetSummary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminDAO {

    java.sql.Connection conn;

    final String tblName = "snippet_list";   // Exact capitalization

    /**
     * Admin (Snippet Summary List) Database Access Object
     */
    public AdminDAO() {
        try  {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
        }
    }

    /**
     * Gets a list of all snippet summary rows
     *
     * @return a List of Snippet Summary Objects
     * @throws Exception thrown if SQL Exception is triggered
     */
    public List<SnippetSummary> getSnippetSummaryList() throws Exception {
        List<SnippetSummary> allSnippets = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("Select * from " + tblName + ";");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SnippetSummary snippetSummary = SnippetSummary.makeSnippetSummary(resultSet);
                allSnippets.add(snippetSummary);
            }

            resultSet.close();
            preparedStatement.close();
            return allSnippets;

        } catch (SQLException e) {
            throw new Exception("Failed in getting all snippet summaries: " + e.getMessage());
        }
    }

    public boolean removeStaleSnippets(int days) throws Exception {
        try {
            PreparedStatement statement = conn.prepareStatement("Delete snippet from snippet_list inner join snippet on ID = Snippet_ID WHERE Last_Modified < DATE_SUB(NOW(), INTERVAL ? DAY);");
            statement.setInt(1, days);
            statement.execute();

            statement.close();

            return true;
        } catch(SQLException e) {
            throw new Exception("Failed in removing stale snippets: " + e.getMessage());
        }
    }
}