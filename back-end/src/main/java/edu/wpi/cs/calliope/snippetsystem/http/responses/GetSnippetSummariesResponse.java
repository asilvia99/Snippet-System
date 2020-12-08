package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class GetSnippetSummariesResponse {

    private final String response;
    private final int httpCode;

    private GetSnippetSummariesResponse(String response, int httpCode) {
        this.response = response;
        this.httpCode = httpCode;
    }

    public static GetSnippetSummariesResponse makeGetSnippetSummariesResponse(String snippets) {
        return new GetSnippetSummariesResponse(snippets, 200);
    }

    public static GetSnippetSummariesResponse makeGetSnippetSummariesResponse(String s_id, int httpCode) {
        return new GetSnippetSummariesResponse(s_id, httpCode);
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
