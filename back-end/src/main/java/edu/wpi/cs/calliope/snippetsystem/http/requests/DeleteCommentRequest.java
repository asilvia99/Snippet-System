package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class DeleteCommentRequest {
    private String cID;

    public DeleteCommentRequest() {

    }

    public DeleteCommentRequest(String cID) {
        this.cID = cID;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    @Override
    public String toString() {
        return "Comment ID: " + cID;
    }
}