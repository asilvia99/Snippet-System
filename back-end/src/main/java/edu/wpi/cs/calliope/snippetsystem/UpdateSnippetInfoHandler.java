package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetComponentResponse;

public class UpdateSnippetInfoHandler implements IUpdateHandler{

    @Override
    public boolean updateSnippetComponent(String id, String codingLang) throws Exception {
        return false;
    }

    @Override
    public UpdateSnippetComponentResponse handleRequest(IUpdateSnippetComponentRequest input, Context context) {
        return null;
    }

}
