package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetPasswordRequest;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetPasswordResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetPasswordHandler implements RequestHandler<UpdateSnippetPasswordRequest, UpdateSnippetPasswordResponse> {

    LambdaLogger logger;

    boolean updateSnippetPassword(String id, String password) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetPassword");
        }
        SnippetDAO dao = new SnippetDAO(logger);

        Snippet exists = dao.getSnippet(id);
        if(exists != null) {
            return dao.updatePassword(id, password);
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
    public UpdateSnippetPasswordResponse handleRequest(UpdateSnippetPasswordRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetPasswordResponse response;
        try {
            if(updateSnippetPassword(input.getID(), input.getPassword())) {
                response = new UpdateSnippetPasswordResponse(input.getID());
            } else {
                response = new UpdateSnippetPasswordResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = new UpdateSnippetPasswordResponse("Unable to update snippet password: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
