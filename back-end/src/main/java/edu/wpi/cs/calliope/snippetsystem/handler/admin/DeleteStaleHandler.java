package edu.wpi.cs.calliope.snippetsystem.handler.admin;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.AdminDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.DeleteStaleRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.DeleteStaleResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class DeleteStaleHandler implements RequestHandler<DeleteStaleRequest, DeleteStaleResponse> {

    LambdaLogger logger;

    /**
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public DeleteStaleResponse handleRequest(DeleteStaleRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        DeleteStaleResponse response;
        try {
            if(deleteStaleSnippets(input.getDays())) {
                response = DeleteStaleResponse.makeDeleteStaleResponse("Response: Successfuly deleted " + input.getDays() + " day old snippets.");
            } else {
                response = DeleteStaleResponse.makeDeleteStaleResponse("Response: Error Deleting Snippets", 442);
            }
        } catch (Exception e) {
            response = DeleteStaleResponse.makeDeleteStaleResponse("Unable to delete stale snippets: (" + e.getMessage() + ")", 400);
        }

        return response;
    }

    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteStaleSnippets(int days) throws Exception {
        if (logger != null) {
            logger.log("In deleteStaleSnippets");
        }
        AdminDAO dao = new AdminDAO();
        return dao.removeStaleSnippets(days);
        
        
    }
}
