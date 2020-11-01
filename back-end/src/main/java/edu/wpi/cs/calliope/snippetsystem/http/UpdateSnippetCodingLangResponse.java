package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetCodingLangResponse {
    private final String response;
    private final int httpCode;

    public UpdateSnippetCodingLangResponse(String id) {
        this.response = id;
        httpCode = 200;
    }

    public UpdateSnippetCodingLangResponse(String id, int code) {
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
