package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class DeleteSnippetRequest {
    private String ID;

    public DeleteSnippetRequest() {

    }

    public DeleteSnippetRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ID: " + ID;
    }
}