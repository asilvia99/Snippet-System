package edu.wpi.cs.calliope.snippetsystem.handler.comment;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.db.CommentDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.CreateCommentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.CreateCommentResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;

import java.util.UUID;

public class CreateCommentHandler implements RequestHandler<CreateCommentRequest, CreateCommentResponse> {

    LambdaLogger logger;

    /**
     * returns true if can add to database
     * @param text
     * @param info
     * @param password
     * @param codingLang
     * @return
     * @throws Exception
     */
    String createComment(String S_ID, String text, String start, String end) throws Exception {
        if (logger != null) {
            logger.log("In createComment");
        }
        CommentDAO dao = new CommentDAO(logger);

        UUID uuid = Generators.timeBasedGenerator().generate();

        logger.log(uuid.toString());

        Gson gson = new Gson();

        Comment exists = dao.getComment(uuid.toString());
        if(exists == null) {
            logger.log("In first case");
            Comment comment = Comment.makeComment(uuid.toString(), S_ID, text, start, end);
            logger.log(comment.getID());
            logger.log("Comment: " + gson.toJson(comment));
            return dao.addComment(comment) ? uuid.toString() : null;
        } else {
        	logger.log("Comment: " + gson.toJson(exists));
            return null;
        }
    }

    /**
     * Handles the request
     *
     * @param input
     * @param context - from AWS
     * @return
     */
    @Override
    public CreateCommentResponse handleRequest(CreateCommentRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        CreateCommentResponse response;
        try {
            String uuid = createComment(input.getSnippetID(), input.getText(), input.getStart(), input.getEnd());

            if(uuid != null) {
                response = CreateCommentResponse.makeCreateCommentResponse(uuid);
            } else {
                response = CreateCommentResponse.makeCreateCommentResponse("UUID is null", 442);
            }
        } catch (Exception e) {
            response = CreateCommentResponse.makeCreateCommentResponse("Unable to create comment: " + "(" + e.getLocalizedMessage() + ")", 400);
        }

        return response;
    }
}
