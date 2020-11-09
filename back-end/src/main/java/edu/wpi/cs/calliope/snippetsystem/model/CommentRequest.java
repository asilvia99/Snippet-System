package edu.wpi.cs.calliope.snippetsystem.model;

public class CommentRequest {
	private String text;
    private String regionStart;
    private String regionEnd;
    
    public CommentRequest(String text, String regionStart, String regionEnd){
		 this.text = text;
		 this.regionStart = regionStart;
		 this.regionEnd = regionEnd;
   }

   public String getText() {
       return text;
   }

   public void setText(String text) {
       this.text = text;
   }

   public String getRegionStart() {
       return regionStart;
   }

   public void setRegionStart(String regionStart) {
       this.regionStart = regionStart;
   }
   public void setRegionEnd(String regionEnd) {
       this.regionEnd = regionEnd;
   }

   public String getRegionEnd() {
       return regionEnd;
   }

   
   @Override
   public String toString() {

       return  "Text: " + getText() +
               "\nRegion Start: " + getRegionStart() +
               "\nRegion End: " + getRegionEnd();
   }
}
