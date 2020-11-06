package edu.wpi.cs.calliope.snippetsystem.http;

public class UpdateSnippetInfoRequest extends IUpdateSnippetComponentRequest{
    private String ID;
    private String codingLang;

    public UpdateSnippetInfoRequest() {
        super();
    }

    public UpdateSnippetInfoRequest(String ID, String codingLang) {
        super(ID);
        this.ID = ID;
        this.codingLang = codingLang;
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

