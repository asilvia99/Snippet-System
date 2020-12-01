package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class AdminPasswordRequest {
    private String password;

    public AdminPasswordRequest() {

    }

    public AdminPasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password: " + password;
    }
}