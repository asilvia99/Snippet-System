package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class DeleteStaleResponse{


	private final String response;
    private final int httpCode;

    public DeleteStaleResponse(String response, int code) {
        this.response = response;
        httpCode = code;
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }

    
    public static DeleteStaleResponse makeDeleteStaleResponse(String response) {
        return new DeleteStaleResponse(response, 200);
    }

    public static DeleteStaleResponse makeDeleteStaleResponse(String message, int code) {
        return new DeleteStaleResponse(message, code);
    }

    @Override
    public String toString() {
        return "Delete Stale Snippet Response(" + this.getResponse() + ")";
    }
}

