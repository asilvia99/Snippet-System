package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.http.ViewSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.ViewSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class ViewSnippetHandler implements RequestHandler<ViewSnippetRequest, ViewSnippetResponse> {

    LambdaLogger logger;

    Snippet viewSnippet(String id) throws Exception {
        if (logger != null) {
            logger.log("In viewSnippet");
        }
        SnippetDAO dao = new SnippetDAO();

        Snippet exists = dao.getSnippet(id);
        return exists;
    }

    /**
     * Handles the request
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public ViewSnippetResponse handleRequest(ViewSnippetRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        ViewSnippetResponse response;
        try {
            Snippet snippet = viewSnippet(input.getId());

            if(snippet != null) {
                response = ViewSnippetResponse.makeViewSnippetResponse(snippet);
            } else {
                response = ViewSnippetResponse.makeViewSnippetResponse(input.getId(), 442);
            }
        } catch (Exception e) {
            response = ViewSnippetResponse.makeViewSnippetResponse("Unable to create snippet: " + input.getId() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
