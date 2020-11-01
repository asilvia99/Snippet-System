package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetPasswordRequest {
    private String ID;
    private String password;

    public UpdateSnippetPasswordRequest() {
    }

    public UpdateSnippetPasswordRequest(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nPassword: " + password;
    }
}

