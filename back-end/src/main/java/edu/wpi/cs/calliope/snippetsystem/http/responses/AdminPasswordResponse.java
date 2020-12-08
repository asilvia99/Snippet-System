package edu.wpi.cs.calliope.snippetsystem.http.responses;

public class AdminPasswordResponse {
    private final String response;
    private final int httpCode;

    private AdminPasswordResponse(String id, int code) {
        this.response = id;
        this.httpCode = code;
    }

    public String getResponse() {
        return response;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public static AdminPasswordResponse makeAdminPasswordResponse(String c_id) {
        return new AdminPasswordResponse(c_id, 200);
    }

    public static AdminPasswordResponse makeAdminPasswordResponse(String message, int code) {
        return new AdminPasswordResponse(message, code);
    }

    @Override
    public String toString() {
        return "Admin Password Response(" + this.getResponse() + ")";
    }
}
