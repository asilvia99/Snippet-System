package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.http.responses.UpdateSnippetComponentResponse;
import edu.wpi.cs.calliope.snippetsystem.http.requests.UpdateSnippetInfoRequest;

public class UpdateSnippetInfoHandler implements IUpdateSnippetHandler, RequestHandler<UpdateSnippetInfoRequest, UpdateSnippetComponentResponse> {

    @Override
    public boolean updateSnippetComponent(String id, String codingLang) throws Exception {
        return false;
    }

    @Override
    public UpdateSnippetComponentResponse handleRequest(UpdateSnippetInfoRequest input, Context context) {
        return null;
    }
}
