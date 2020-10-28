package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetTextRequest {
    private String ID;
    private String text;

    public UpdateSnippetTextRequest() {
    }

    public UpdateSnippetTextRequest(String ID, String text) {
        this.ID = ID;
        this.text = text;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

