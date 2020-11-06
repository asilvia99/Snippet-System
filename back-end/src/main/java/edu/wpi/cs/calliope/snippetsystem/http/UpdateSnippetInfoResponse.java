package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetInfoResponse implements IUpdateSnippetComponentResponse{
    private final String response;
    private final int httpCode;

    public UpdateSnippetInfoResponse(String id) {
        this.response = id;
        httpCode = 200;
    }

    public UpdateSnippetInfoResponse(String id, int code) {
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
