package edu.wpi.cs.calliope.snippetsystem.http;

public class ViewSnippetRequest {
    private final String id;

    public ViewSnippetRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
