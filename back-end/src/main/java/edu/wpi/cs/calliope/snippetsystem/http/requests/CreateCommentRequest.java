package edu.wpi.cs.calliope.snippetsystem.http.requests;



public class CreateCommentRequest {
    private String S_ID;
    private String text;
    private String start;
    private String end;

    public CreateCommentRequest(){

    }

    public CreateCommentRequest(String S_ID, String text, String start, String end){
        this.S_ID = S_ID;
        this.text = text;
        this.start = start;
        this.end = end;
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String s_ID) {
        S_ID = s_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return  "S_ID: " + getS_ID() +
                "\nText: " + getText() +
                "\nStart Region: " + getStart() +
                "\nEnd Region: " + getEnd();
    }
}
