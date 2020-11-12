package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class UpdateSnippetInfoRequest{
    private String ID;
    private String info;

    public UpdateSnippetInfoRequest() {
    }

    public UpdateSnippetInfoRequest(String ID, String info) {
        this.ID = ID;
        this.info = info;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nInfo: " + info;
    }
}

