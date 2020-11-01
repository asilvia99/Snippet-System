package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetPasswordResponse {
    private final String response;
    private final int httpCode;

    public UpdateSnippetPasswordResponse(String id) {
        this.response = id;
        httpCode = 200;
    }

    public UpdateSnippetPasswordResponse(String id, int code) {
        this.response = id;
        httpCode = code;
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String toString() {
        return "Response(" + response + ")";
    }
}
