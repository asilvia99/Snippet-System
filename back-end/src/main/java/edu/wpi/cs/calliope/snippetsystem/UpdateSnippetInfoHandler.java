package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentResponse;

public class UpdateSnippetInfoHandler implements IUpdateHandler{

    @Override
    public boolean updateSnippetComponent(String id, String codingLang) throws Exception {
        return false;
    }

    @Override
    public IUpdateSnippetComponentResponse handleRequest(IUpdateSnippetComponentRequest input, Context context) {
        return null;
    }

}
