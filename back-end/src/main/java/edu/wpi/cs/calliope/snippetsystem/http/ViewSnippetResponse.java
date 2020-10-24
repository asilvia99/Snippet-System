package edu.wpi.cs.calliope.snippetsystem.http;

import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class ViewSnippetResponse {
    private Snippet snippet;
    private String message;

    private final int httpCode;

    private ViewSnippetResponse(Snippet snippet, int httpCode) {
        this.snippet = snippet;
        this.httpCode = httpCode;
    }

    private ViewSnippetResponse(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public static ViewSnippetResponse makeViewSnippetResponse(Snippet snippet) {
        return new ViewSnippetResponse(snippet, 200);
    }

    public static ViewSnippetResponse makeViewSnippetResponse(String message, int code) {
        return new ViewSnippetResponse(message, code);
    }

    @Override
    public String toString() {
        if(this.snippet != null) {
            return "Response("+ snippet.toJSON() + ")";
        } else {
            return "Response(" + message + ")";
        }
    }
}
