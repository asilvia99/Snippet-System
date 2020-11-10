package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class GetCommentsRequest {
    private String snippetID;

    public GetCommentsRequest() {

    }

    public GetCommentsRequest(String snippetID) {
        this.snippetID = snippetID;
    }

    public String getSnippetID() {
        return snippetID;
    }

    public void setSnippetID(String snippetID) {
        this.snippetID = snippetID;
    }

    @Override
    public String toString() {
        return snippetID;
    }
}
