package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class DeleteCommentResponse {

	private final String response;
	private final int httpCode;
	
	public DeleteCommentResponse(String id, int code) {
        this.response = id;
        httpCode = code;
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }

   
    public static DeleteCommentResponse makeDeleteCommentResponse(String id) {
        return new DeleteCommentResponse(id, 200);
    }

    public static DeleteCommentResponse makeDeleteCommentResponse(String message, int code) {
        return new DeleteCommentResponse(message, code);
    }

    @Override
    public String toString() {
        return "Delete Comment Response(" + this.getResponse() + ")";
    }
}
