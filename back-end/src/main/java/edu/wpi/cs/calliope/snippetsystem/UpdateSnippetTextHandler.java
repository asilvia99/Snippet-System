package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetTextRequest;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetTextResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetTextHandler implements RequestHandler<UpdateSnippetTextRequest, UpdateSnippetTextResponse> {

    LambdaLogger logger;

    boolean updateSnippetText(String id, String text) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetText");
        }
        SnippetDAO dao = new SnippetDAO();

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
    public UpdateSnippetTextResponse handleRequest(UpdateSnippetTextRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetTextResponse response;
        try {
            if(updateSnippetText(input.getID(), input.getText())) {
                response = new UpdateSnippetTextResponse(input.getID());
            } else {
                response = new UpdateSnippetTextResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = new UpdateSnippetTextResponse("Unable to update snippet text: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
