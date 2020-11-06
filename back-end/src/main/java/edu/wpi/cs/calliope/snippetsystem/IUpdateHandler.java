package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.IUpdateSnippetComponentResponse;


public interface IUpdateHandler extends RequestHandler<IUpdateSnippetComponentRequest, IUpdateSnippetComponentResponse> {

    boolean updateSnippetComponent(String id, String str) throws Exception;

    @Override
    IUpdateSnippetComponentResponse handleRequest(IUpdateSnippetComponentRequest input, Context context);
}
