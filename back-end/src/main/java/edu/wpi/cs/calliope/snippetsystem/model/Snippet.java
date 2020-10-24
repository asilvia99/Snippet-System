package edu.wpi.cs.calliope.snippetsystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class Snippet {

    private final String id;
    private String text;
    private String info;
    private final String password;
    private final String codingLang;
    private final Instant created;
    private Instant modified;

    private Snippet(String id, String text, String info, String password, String codingLang, Instant created, Instant modified) {
        this.id = id;
        this.text = text;
        this.info = info;
        this.password = password;
        this.codingLang = codingLang;
        this.created = created;
        this.modified = modified;
    }

    public static Snippet makeSnippet(ResultSet resultSet) throws Exception {
        try {
            String id = resultSet.getString(1);
            String text = resultSet.getString(2);
            String info = resultSet.getString(3);
            String password = resultSet.getString(4);
            String codingLang = resultSet.getString(5);
            Instant created = resultSet.getTimestamp(6).toInstant();
            Instant modified = resultSet.getTimestamp(7).toInstant();
            return new Snippet(id, text, info, password, codingLang, created, modified);
        } catch (SQLException e) {
            throw new Exception("Snippet could not be created from Result Set: " + e.getMessage());
        }
    }

    public static Snippet makeSnippet(String id, String text, String info, String password, String codingLang) throws Exception {
        return new Snippet(id, text, info, password, codingLang, null, null);
    }

    public String getID() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPassword() {
        return password;
    }

    public String getCodingLang() {
        return codingLang;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String toJSON() {
        return "{" +
                "id: " + getID() + "," +
                "text: " + getText() + "," +
                "info: " + getInfo() + "," +
                "password: " + getPassword() + "," +
                "codingLanguage: " + getCodingLang() + "," +
                "createDate: " + getCreated().toString() + "," +
                "lastModifiedDate: " + getModified().toString() + "," +
                "}";
    }
}
