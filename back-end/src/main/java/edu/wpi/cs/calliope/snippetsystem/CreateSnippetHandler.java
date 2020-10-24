package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

public class CreateSnippetHandler implements RequestHandler<CreateSnippetRequest, CreateSnippetResponse> {

    LambdaLogger logger;

    /**
     * returns true if can add to database
     * @param id
     * @param text
     * @param info
     * @param password
     * @param codingLang
     * @return
     * @throws Exception
     */
    boolean createSnippet(String id, String text, String info, String password, String codingLang) throws Exception {
        if (logger != null) {
            logger.log("In createSnippet");
        }
        SnippetDAO dao = new SnippetDAO();

        Snippet exists = dao.getSnippet(id);
        if(exists == null) {
            Snippet snippet = Snippet.makeSnippet(id, text, info, password, codingLang);
            return dao.addSnippet(snippet);
        } else {
            return false;
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
    public CreateSnippetResponse handleRequest(CreateSnippetRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        CreateSnippetResponse response;
        try {
            if(createSnippet(input.getID(), input.getText(), input.getInfo(), input.getPassword(), input.getCodingLang())) {
                response = new CreateSnippetResponse(input.getID());
            } else {
                response = new CreateSnippetResponse(input.getID(), 442);
            }
        } catch (Exception e) {
            response = new CreateSnippetResponse("Unable to create snippet: " + input.getID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
