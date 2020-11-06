package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class UpdateSnippetComponentResponse extends SnippetResponse {
    private UpdateSnippetComponentResponse(String id, int code) {
        super(id, code);
    }

    public static UpdateSnippetComponentResponse makeSnippetComponentResponse(String snippet) {
        return new UpdateSnippetComponentResponse(snippet, 200);
    }

    public static UpdateSnippetComponentResponse makeSnippetComponentResponse(String message, int code) {
        return new UpdateSnippetComponentResponse(message, code);
    }

    @Override
    public String toString() {
        return "Component Response(" + this.getResponse() + ")";
    }
}
