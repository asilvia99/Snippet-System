package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class GetCommentsResponse {

    private final String response;
    private final int httpCode;

    private GetCommentsResponse(String response, int httpCode) {
        this.response = response;
        this.httpCode = httpCode;
    }

    public static GetCommentsResponse makeGetCommentsResponse(String comments) {
        return new GetCommentsResponse(comments, 200);
    }

    public static GetCommentsResponse makeGetCommentsResponse(String s_id, int httpCode) {
        return new GetCommentsResponse(s_id, httpCode);
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
