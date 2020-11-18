package edu.wpi.cs.calliope.snippetsystem.db;

import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gets a snippet from the database
 */
public class SnippetDAO {

    java.sql.Connection conn;

    final String tblName = "snippet";   // Exact capitalization

    /**
     * Snippet Database Access Object
     */
    public SnippetDAO() {
        try  {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
        }
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

            return numAffected == 1;
        } catch (SQLException e) {
            throw new Exception("Failed to update snippet text: " + e.getMessage());
        }
    }

    /**
     * Updates the password of a snippet
     *
     * @param id the id of the snippet
     * @param password the password to update
     * @return true if the number of snippets updated equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean updatePassword(String id, String password) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + tblName + " SET Password=? WHERE ID=?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, id);

            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return numAffected == 1;
        } catch (SQLException e) {
            throw new Exception("Failed to update snippet password: " + e.getMessage());
        }
    }

    /**
     * Updates the codingLang of a snippet
     *
     * @param id the id of the snippet
     * @param codingLang the coding lang to update
     * @return true if the number of snippets updated equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean updateCodingLang(String id, String codingLang) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE " + tblName + " SET Coding_Lang=? WHERE ID=?");
            preparedStatement.setString(1, codingLang);
            preparedStatement.setString(2, id);

            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return numAffected == 1;
        } catch (SQLException e) {
            throw new Exception("Failed to update snippet coding language: " + e.getMessage());
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
}