package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class CreateCommentResponse {

	private final String response;
    private final int httpCode;
    
    private CreateCommentResponse(String id, int code) {
    	this.response = id;
        this.httpCode = code;
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public static CreateCommentResponse makeCreateCommentResponse(String snippet) {
        return new CreateCommentResponse(snippet, 200);
    }

    public static CreateCommentResponse makeCreateCommentResponse(String message, int code) {
        return new CreateCommentResponse(message, code);
    }

    @Override
    public String toString() {
        return "Create Comment Response(" + this.getResponse() + ")";
    }
}
