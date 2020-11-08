package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class DeleteSnippetResponse extends SnippetResponse{

    public DeleteSnippetResponse(String id, int code) {
        super(id, code);
    }

    public static DeleteSnippetResponse makeDeleteSnippetResponse(String id) {
        return new DeleteSnippetResponse(id, 200);
    }

    public static DeleteSnippetResponse makeDeleteSnippetResponse(String message, int code) {
        return new DeleteSnippetResponse(message, code);
    }

    @Override
    public String toString() {
        return "Delete Snippet Response(" + this.getResponse() + ")";
    }
}
