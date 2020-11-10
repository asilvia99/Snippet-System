package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class ViewSnippetRequest {
    private String ID;

    public ViewSnippetRequest() {

    }

    public ViewSnippetRequest(String ID) {
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
        return ID;
    }
}
