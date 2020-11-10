package edu.wpi.cs.calliope.snippetsystem.db;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets a comment from the database
 */
public class CommentDAO {

    private final LambdaLogger logger;
    Connection conn;

    final String tblName = "comment";   // Exact capitalization

    /**
     * Comment Database Access Object
     */
    public CommentDAO(LambdaLogger logger) {
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
     * Deletes a comment from the database
     *
     * @param id the id of the comment
     * @return true if the number of comments deleted equals 1
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean deleteComment(String id) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE From " + tblName + " Where ID=?;");
            preparedStatement.setString(1, id);
            int numAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return (numAffected == 1);
        } catch (SQLException e) {
            throw new Exception("Failed to delete comment: " + e.getMessage());
        }
    }

    /**
     * Adds a comment to the database
     *
     * @param comment the comment to add
     * @return true if inserted, false if already exists
     * @throws Exception thrown if SQL Exception is triggered
     */
    public boolean addComment(Comment comment) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ID=?;");
            preparedStatement.setString(1, comment.getID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                resultSet.close();
                return false;
            }

            preparedStatement = conn.prepareStatement("INSERT INTO " + tblName + "(ID, S_ID, Text, Start, End) value (?,?,?,?,?)");
            preparedStatement.setString(1, comment.getID());
            preparedStatement.setString(2, comment.getS_ID());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setString(4, comment.getStart());
            preparedStatement.setString(5, comment.getEnd());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception("Failed to create a comment: " + e.getMessage());
        }
    }

    public Comment getComment(String id) throws Exception {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("Select * from " + tblName + " where ID=?;");
            ResultSet resultSet = preparedStatement.executeQuery();

            Comment comment = null;

            while (resultSet.next()) {
                comment = Comment.makeComment(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
            return comment;

        } catch (SQLException e) {
            throw new Exception("Failed in getting the comment with the specified id: " + e.getMessage());
        }
    }

    /**
     * Gets a list of all comments for a specified Snippet ID
     *
     * @return a list of Comments
     * @throws Exception thrown if SQL Exception is triggered
     */
    public List<Comment> getAllComments(String s_id) throws Exception {
        List<Comment> allComments = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("Select * from " + tblName + " where S_ID=?;");
            preparedStatement.setString(1, s_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = Comment.makeComment(resultSet);
                allComments.add(comment);
            }

            resultSet.close();
            preparedStatement.close();
            return allComments;

        } catch (SQLException e) {
            throw new Exception("Failed in getting all comments for the specified s_id: " + e.getMessage());
        }
    }

}