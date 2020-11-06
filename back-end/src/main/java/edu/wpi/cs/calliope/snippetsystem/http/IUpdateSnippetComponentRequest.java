package edu.wpi.cs.calliope.snippetsystem.http;

public abstract class IUpdateSnippetComponentRequest {

    private String ID;

    public IUpdateSnippetComponentRequest() {

    }

    public IUpdateSnippetComponentRequest(String ID){
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
