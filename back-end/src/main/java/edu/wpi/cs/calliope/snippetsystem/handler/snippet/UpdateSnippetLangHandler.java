package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.UpdateSnippetCodingLangRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.UpdateSnippetComponentResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetLangHandler implements IUpdateSnippetHandler, RequestHandler<UpdateSnippetCodingLangRequest, UpdateSnippetComponentResponse> {

    LambdaLogger logger;

    @Override
    public boolean updateSnippetComponent(String id, String codingLang) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetLang");
        }
        SnippetDAO dao = new SnippetDAO();

        Snippet exists = dao.getSnippet(id);
        if(exists != null) {
            return dao.updateCodingLang(id, codingLang);
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
    public UpdateSnippetComponentResponse handleRequest(UpdateSnippetCodingLangRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetComponentResponse response;
        try {
            if(updateSnippetComponent(input.getID(), input.getCodingLang())) {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID());
            } else {
                response = UpdateSnippetComponentResponse.makeSnippetComponentResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = UpdateSnippetComponentResponse.makeSnippetComponentResponse("Unable to update snippet coding language: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
