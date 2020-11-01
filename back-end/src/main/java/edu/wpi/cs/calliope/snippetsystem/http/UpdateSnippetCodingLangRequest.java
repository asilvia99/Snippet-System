package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetCodingLangRequest {
    private String ID;
    private String codingLang;

    public UpdateSnippetCodingLangRequest() {
    }

    public UpdateSnippetCodingLangRequest(String ID, String codingLang) {
        this.ID = ID;
        this.codingLang = codingLang;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCodingLang() {
        return codingLang;
    }

    public void setCodingLang(String codingLang) {
        this.codingLang = codingLang;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nCoding Language: " + codingLang;
    }
}

