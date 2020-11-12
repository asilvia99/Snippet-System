package edu.wpi.cs.calliope.snippetsystem.http.requests;



public class CreateCommentRequest {
    private String snippetID;
    private String text;
    private String start;
    private String end;

    public CreateCommentRequest(){

    }

    public CreateCommentRequest(String snippetID, String text, String start, String end){
        this.snippetID = snippetID;
        this.text = text;
        this.start = start;
        this.end = end;
    }

    public String getSnippetID() {
        return snippetID;
    }

    public void setSnippetID(String snippetID) {
        this.snippetID = snippetID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return  "Snippet ID: " + getSnippetID() +
                "\nText: " + getText() +
                "\nStart Region: " + getStart() +
                "\nEnd Region: " + getEnd();
    }
}
