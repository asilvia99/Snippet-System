package edu.wpi.cs.calliope.snippetsystem.handler.comment;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.db.CommentDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.GetCommentsRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.GetCommentsResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;

import java.util.List;

public class GetCommentsHandler implements RequestHandler<GetCommentsRequest, GetCommentsResponse> {

    LambdaLogger logger;

    List<Comment> getComments(String s_id) throws Exception {
        if (logger != null) {
            logger.log("In getComments");
        }
        CommentDAO dao = new CommentDAO(logger);

        return dao.getAllComments(s_id);
    }

    /**
     * Handles the request
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public GetCommentsResponse handleRequest(GetCommentsRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        GetCommentsResponse response;
        try {
            List<Comment> comments = getComments(input.getS_ID());

            if(comments != null) {
                Gson gson = new Gson();
                response = GetCommentsResponse.makeGetCommentsResponse(gson.toJson(comments));
            } else {
                response = GetCommentsResponse.makeGetCommentsResponse(input.getS_ID(), 442);
            }
        } catch (Exception e) {
            response = GetCommentsResponse.makeGetCommentsResponse("Unable to create snippet: " + input.getS_ID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
