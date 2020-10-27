package edu.wpi.cs.calliope.snippetsystem.http;

public class CreateSnippetRequest {
    private String ID;
    private String text;
    private String info;
    private String password;
    private String codingLang;

    public CreateSnippetRequest(){
	
    }
    
    public CreateSnippetRequest(String id,  String info, String text, String codingLang, String password){
		 this.ID = id;
		 this.text = text;
		 this.info = info;
		 this.password = password;
		 this.codingLang = codingLang;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodingLang() {
        return codingLang;
    }

    public void setCodingLang(String codingLang) {
        this.codingLang = codingLang;
    }

    @Override
    public String toString() {

        return "ID: " + getID() +
                "\nText: " + getText() +
                "\nInfo: " + getInfo() +
                "\nPassword: " + getPassword() +
                "\nCoding Lang: " + getCodingLang();
    }
}
