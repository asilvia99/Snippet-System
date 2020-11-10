package edu.wpi.cs.calliope.snippetsystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class SnippetSummary {
    private final String ID;
    private final int commentCount;
    private final String info;
    private final Instant created;
    private final Instant lastModified;

    private SnippetSummary(String ID, int commentCount, String info, Instant created, Instant lastModified) {
        this.ID = ID;
        this.commentCount = commentCount;
        this.info = info;
        this.created = created;
        this.lastModified = lastModified;
    }

    public static SnippetSummary makeSnippetSummary(ResultSet resultSet) {
        try {
            String id = resultSet.getString(1);
            int commentCount = resultSet.getInt(2);
            String info = resultSet.getString(3);
            Instant created = resultSet.getTimestamp(4).toInstant();
            Instant lastModified = resultSet.getTimestamp(5).toInstant();
            return new SnippetSummary(id, commentCount, info, created, lastModified);
        } catch (SQLException e) {
            return null;
        }
    }

    public String getID() {
        return ID;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getInfo() {
        return info;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getLastModified() {
        return lastModified;
    }
}
