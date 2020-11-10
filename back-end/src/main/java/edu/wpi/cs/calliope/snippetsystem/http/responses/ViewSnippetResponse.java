package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class ViewSnippetResponse extends SnippetResponse{

    private ViewSnippetResponse(String message, int httpCode) {
        super(message, httpCode);
    }

    public static ViewSnippetResponse makeViewSnippetResponse(String snippet) {
        return new ViewSnippetResponse(snippet, 200);
    }

    public static ViewSnippetResponse makeViewSnippetResponse(String message, int code) {
        return new ViewSnippetResponse(message, code);
    }
}
