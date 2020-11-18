package edu.wpi.cs.calliope.snippetsystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class Comment {

    private final String ID;
    private final String snippetID;
    private final String text;
    private final String start;
    private final String end;
    private final Instant created;

    private Comment(String id, String s_id, String text, String start, String end, Instant created) {
        this.ID = id;
        this.snippetID = s_id;
        this.text = text;
        this.start = start;
        this.end = end;
        this.created = created;
    }

    public static Comment makeComment(ResultSet resultSet) throws Exception {
        try {
            String id = resultSet.getString(1);
            String s_id = resultSet.getString(2);
            String text = resultSet.getString(3);
            String start = resultSet.getString(4);
            String end = resultSet.getString(5);
            Instant created = resultSet.getTimestamp(6).toInstant();
            return new Comment(id, s_id, text, start, end, created);
        } catch (SQLException e) {
            throw new Exception("Comment could not be created from Result Set: " + e.getMessage());
        }
    }

    public static Comment makeComment(String id, String s_id, String text, String start, String end) {
        return new Comment(id, s_id, text, start, end, null);
    }

    public String getID() {
        return ID;
    }

    public String getSnippetID() {
        return snippetID;
    }

    public String getText() {
        return text;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Instant getCreated() {
        return created;
    }
}
