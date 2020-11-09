package edu.wpi.cs.calliope.snippetsystem.http.requests;

import edu.wpi.cs.calliope.snippetsystem.model.CommentRequest;


public class CreateCommentRequest {
    // So tbh, im not really sure if this is right... we haven't had many other requests in this format but I tried my best
	// CommentRequest is in the model package
	private String sID;
    private CommentRequest CommentRequest;

    public CreateCommentRequest(){
	
    }
    
    public CreateCommentRequest(String sID, CommentRequest CommentRequest){
		 this.sID = sID;
		 this.CommentRequest = CommentRequest;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public CommentRequest getCommentRequest() {
        return this.CommentRequest;
    }

    public void setCommentRequest(CommentRequest CommentRequest) {
		 this.CommentRequest = CommentRequest;
    }
    
    
    @Override
    public String toString() {

        return  "sID: " + getsID() +
                "\nComment Request: " + getCommentRequest().toString();
    }
}
