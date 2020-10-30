package edu.wpi.cs.calliope.snippetsystem.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

/**
 * Gets a snippet from the database
 */
public class SnippetDAO {

    private final LambdaLogger logger;
    java.sql.Connection conn;

    final String tblName = "snippet";   // Exact capitalization

    /**
     * Snippet Database Access Object
     */
    public SnippetDAO(LambdaLogger logger) {
        this.logger = logger;
        try  {
            conn = DatabaseUtil.connect(logger);
        } catch (Exception e) {
        	logger.log(e.getLocalizedMessage());
            conn = null;
        }
        logger.log("Conn: " + (conn != null));
    }

    /**
     * Gets a snippet with a specified ID
     *
     * @param id the ID of the snippet to return
     * @return a Snippet Object
     * @throws Exception thrown if SQL Exception is triggered
     */
    public Snippet getSnippet(String id) throws Exception {
        try {
            Snippet snippet = null;
            PreparedStatement preparedStatement = conn.prepareStatement("Select * From " + tblName + " WHERE ID=?;");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next() && !resultSet.wasNull()) {
                snippet = Snippet.makeSnippet(resultSet);
            }

            resultSet.close();
            preparedStatement.close();

            return snippet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the text of a snippet
     *
     * @param id the id of the snippet
     * @param text the text to update
     * @return true if the number of snippets updated equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean updateText(String id, String text) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + tblName + " SET Text=? WHERE ID=?");
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, id);

            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.log(String.valueOf(numAffected));

            return numAffected == 1;
        } catch (SQLException e) {
            logger.log("Failed to update snippet text: " + e.getMessage());
            throw new Exception("Failed to update snippet text: " + e.getMessage());
        }
    }

    /**
     * Updates the info of a snippet
     *
     * @param id the id of the snippet
     * @param info the info to update
     * @return true if the number of snippets updated equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean updateInfo(String id, String info) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + tblName + " SET Info=? WHERE ID=?");
            preparedStatement.setString(1, info);
            preparedStatement.setString(2, id);

            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return numAffected == 1;
        } catch (SQLException e) {
            throw new Exception("Failed to update snippet info: " + e.getMessage());
        }
    }

    /**
     * Deletes a snippet from the database
     *
     * @param id the id of the snippet
     * @return true if the number of snippets deleted equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean deleteSnippet(String id) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE From " + tblName + " Where ID=?;");
            preparedStatement.setString(1, id);
            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return (numAffected == 1);
        } catch (SQLException e) {
            throw new Exception("Failed to delete snippet: " + e.getMessage());
        }
    }

    /**
     * Adds a snippet to the database
     *
     * @param snippet the snippet to add
     * @return true if inserted, false if already exists
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean addSnippet(Snippet snippet) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ID=?;");
            preparedStatement.setString(1, snippet.getID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                resultSet.close();
                return false;
            }

            preparedStatement = conn.prepareStatement("INSERT INTO " + tblName + "(ID, Text, Info, Password, Coding_Lang) value (?,?,?,?,?)");
            preparedStatement.setString(1, snippet.getID());
            preparedStatement.setString(2, snippet.getText());
            preparedStatement.setString(3, snippet.getInfo());
            preparedStatement.setString(4, snippet.getPassword());
            preparedStatement.setString(5, snippet.getCodingLang());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception("Failed to create snippet: " + e.getMessage());
        }
    }

    /**
     * Gets a list of all snippets
     *
     * @return a list of Snippets
     * @throws Exception thrown if SQL Exception is triggered
     */
    public List<Snippet> getAllSnippets() throws Exception {
        List<Snippet> allSnippets = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("Select * from " + tblName + ";");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Snippet snippet = Snippet.makeSnippet(resultSet);
                allSnippets.add(snippet);
            }

            resultSet.close();
            preparedStatement.close();
            return allSnippets;

        } catch (SQLException e) {
            throw new Exception("Failed in getting all snippets: " + e.getMessage());
        }
    }

}