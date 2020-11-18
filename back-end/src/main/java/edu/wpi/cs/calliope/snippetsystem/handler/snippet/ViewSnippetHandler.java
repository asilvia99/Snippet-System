package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.ViewSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.ViewSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class ViewSnippetHandler implements RequestHandler<ViewSnippetRequest, ViewSnippetResponse> {

    LambdaLogger logger;

    Snippet viewSnippet(String id) throws Exception {
        if (logger != null) {
            logger.log("In viewSnippet");
        }
        SnippetDAO dao = new SnippetDAO();

        return dao.getSnippet(id);
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
            Snippet snippet = viewSnippet(input.getID());

            if(snippet != null) {
                Gson gson = new Gson();
                response = ViewSnippetResponse.makeViewSnippetResponse(gson.toJson(snippet));
            } else {
                response = ViewSnippetResponse.makeViewSnippetResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = ViewSnippetResponse.makeViewSnippetResponse("Unable to create snippet: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
