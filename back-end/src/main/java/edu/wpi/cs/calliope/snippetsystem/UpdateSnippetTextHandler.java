package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.responses.UpdateSnippetComponentResponse;
import edu.wpi.cs.calliope.snippetsystem.http.requests.UpdateSnippetTextRequest;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetTextHandler implements IUpdateSnippetHandler, RequestHandler<UpdateSnippetTextRequest, UpdateSnippetComponentResponse> {

    LambdaLogger logger;

    @Override
    public boolean updateSnippetComponent(String id, String text) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetText");
        }
        SnippetDAO dao = new SnippetDAO(logger);

        Snippet exists = dao.getSnippet(id);
        if(exists != null) {
            return dao.updateText(id, text);
        } else {
            return false;
        }
    }

    /**
     * Handles the request
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public UpdateSnippetComponentResponse handleRequest(UpdateSnippetTextRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetComponentResponse response;
        try {
            if(updateSnippetComponent(input.getID(), input.getText())) {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID());
            } else {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = UpdateSnippetComponentResponse.makeSnippetComponentResponse("Unable to update snippet text: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
