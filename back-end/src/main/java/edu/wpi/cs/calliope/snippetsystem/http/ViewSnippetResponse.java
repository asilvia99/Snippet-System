package edu.wpi.cs.calliope.snippetsystem.http;

import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class ViewSnippetResponse {
    private Snippet snippet;
    private String message;

    private final int httpCode;

    private Gson gson;

    private ViewSnippetResponse(Snippet snippet, int httpCode) {
        this.snippet = snippet;
        this.httpCode = httpCode;

        gson = new Gson();
    }

    private ViewSnippetResponse(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public static ViewSnippetResponse makeViewSnippetResponse(Snippet snippet) {
        return new ViewSnippetResponse(snippet, 200);
    }

    public static ViewSnippetResponse makeViewSnippetResponse(String message, int code) {
        return new ViewSnippetResponse(message, code);
    }

    public String getResponse() {
        if(this.snippet != null) {
            return gson.toJson(snippet);
        } else {
            return message;
        }
    }

    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String toString() {
        if(this.snippet != null) {
            return "Response("+ gson.toJson(snippet) + ")";
        } else {
            return "Response(" + message + ")";
        }
    }
}
