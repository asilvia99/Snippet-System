package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.DeleteSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.DeleteSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class DeleteSnippetHandler implements RequestHandler<DeleteSnippetRequest, DeleteSnippetResponse> {

    LambdaLogger logger;

    /**
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public DeleteSnippetResponse handleRequest(DeleteSnippetRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        DeleteSnippetResponse response;
        try {
            if(deleteSnippet(input.getID())) {
                response = DeleteSnippetResponse.makeDeleteSnippetResponse(input.getID());
            } else {
                response = DeleteSnippetResponse.makeDeleteSnippetResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = DeleteSnippetResponse.makeDeleteSnippetResponse("Unable to delete snippet: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }

    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteSnippet(String id) throws Exception {
        if (logger != null) {
            logger.log("In deleteSnippet");
        }
        SnippetDAO dao = new SnippetDAO(logger);

        Snippet exists = dao.getSnippet(id);
        if(exists != null) {
            return dao.deleteSnippet(id);
        } else {
            return false;
        }
    }
}
