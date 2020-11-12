package edu.wpi.cs.calliope.snippetsystem.handler.comment;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.CommentDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.DeleteCommentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.DeleteCommentResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;

public class DeleteCommentHandler implements RequestHandler<DeleteCommentRequest, DeleteCommentResponse> {

    LambdaLogger logger;

    /**
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public DeleteCommentResponse handleRequest(DeleteCommentRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        DeleteCommentResponse response;
        try {
            if(deleteComment(input.getcID())) {
                response = DeleteCommentResponse.makeDeleteCommentResponse(input.getcID());
            } else {
                response = DeleteCommentResponse.makeDeleteCommentResponse(input.getcID(), 442);
            }
        } catch (Exception e) {
            response = DeleteCommentResponse.makeDeleteCommentResponse("Unable to delete comment: " + input.getcID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }

    //TODO: DAO.getComment doesn't exist
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteComment(String id) throws Exception {
        if (logger != null) {
            logger.log("In deleteComment");
        }
        CommentDAO dao = new CommentDAO();

        Comment exists = dao.getComment(id);
        if(exists != null) {
            return dao.deleteComment(id);
        } else {
            return false;
        }
    }
}
