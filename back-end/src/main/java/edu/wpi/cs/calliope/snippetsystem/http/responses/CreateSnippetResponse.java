package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class CreateSnippetResponse extends SnippetResponse{

    private CreateSnippetResponse(String id, int code) {
        super(id, code);
    }

    public static CreateSnippetResponse makeCreateSnippetResponse(String snippet) {
        return new CreateSnippetResponse(snippet, 200);
    }

    public static CreateSnippetResponse makeCreateSnippetResponse(String message, int code) {
        return new CreateSnippetResponse(message, code);
    }

    @Override
    public String toString() {
        return "Create Snippet Response(" + this.getResponse() + ")";
    }
}
