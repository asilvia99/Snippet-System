package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetTextResponse {
    private final String response;
    private final int httpCode;

    public UpdateSnippetTextResponse(String id) {
        this.response = id;
        httpCode = 200;
    }

    public UpdateSnippetTextResponse(String id, int code) {
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
