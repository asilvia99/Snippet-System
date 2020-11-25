package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class DeleteStaleRequest {
    private int days;

    public DeleteStaleRequest() {

    }

    public DeleteStaleRequest(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Days: " + days;
    }
}