package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetComponentResponse;


public interface IUpdateHandler extends RequestHandler<IUpdateSnippetComponentRequest, UpdateSnippetComponentResponse> {

    boolean updateSnippetComponent(String id, String str) throws Exception;

    @Override
    UpdateSnippetComponentResponse handleRequest(IUpdateSnippetComponentRequest input, Context context);
}
