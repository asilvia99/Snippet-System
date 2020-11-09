package edu.wpi.cs.calliope.snippetsystem.http.requests;

public class GetCommentsRequest {
    private String S_ID;

    public GetCommentsRequest() {

    }

    public GetCommentsRequest(String S_ID) {
        this.S_ID = S_ID;
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String s_ID) {
        this.S_ID = s_ID;
    }

    @Override
    public String toString() {
        return S_ID;
    }
}
