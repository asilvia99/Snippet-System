package edu.wpi.cs.calliope.snippetsystem.http.responses;

public abstract class SnippetResponse {
    private final String response;
    private final int httpCode;

    public SnippetResponse(String id, int code) {
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
