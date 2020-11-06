package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetComponentResponse;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetPasswordRequest;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetPasswordHandler implements IUpdateSnippetHandler, RequestHandler<UpdateSnippetPasswordRequest, UpdateSnippetComponentResponse> {

    LambdaLogger logger;

    @Override
    public boolean updateSnippetComponent(String id, String str) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetPassword");
        }
        SnippetDAO dao = new SnippetDAO(logger);

        Snippet exists = dao.getSnippet(id);
        if(exists != null) {
            return dao.updatePassword(id, str);
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
    public UpdateSnippetComponentResponse handleRequest(UpdateSnippetPasswordRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetComponentResponse response;
        try {
            if(updateSnippetComponent(input.getID(), input.getPassword())) {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID());
            } else {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = UpdateSnippetComponentResponse.makeSnippetComponentResponse("Unable to update snippet password: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
