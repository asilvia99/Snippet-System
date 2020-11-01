package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetCodingLangRequest;
import edu.wpi.cs.calliope.snippetsystem.http.UpdateSnippetCodingLangResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class UpdateSnippetLangHandler implements RequestHandler<UpdateSnippetCodingLangRequest, UpdateSnippetCodingLangResponse> {

    LambdaLogger logger;

    boolean updateSnippetCodingLang(String id, String codingLang) throws Exception {
        if (logger != null) {
            logger.log("In updateSnippetLang");
        }
        SnippetDAO dao = new SnippetDAO(logger);

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
    public UpdateSnippetCodingLangResponse handleRequest(UpdateSnippetCodingLangRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        UpdateSnippetCodingLangResponse response;
        try {
            if(updateSnippetCodingLang(input.getID(), input.getCodingLang())) {
                response = new UpdateSnippetCodingLangResponse(input.getID());
            } else {
                response = new UpdateSnippetCodingLangResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = new UpdateSnippetCodingLangResponse("Unable to update snippet coding language: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
