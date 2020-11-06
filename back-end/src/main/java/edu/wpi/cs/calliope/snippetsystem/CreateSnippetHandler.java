package edu.wpi.cs.calliope.snippetsystem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

import java.util.UUID;

public class CreateSnippetHandler implements RequestHandler<CreateSnippetRequest, CreateSnippetResponse> {

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
    String createSnippet(String text, String info, String password, String codingLang) throws Exception {
        if (logger != null) {
            logger.log("In createSnippet");
        }
        SnippetDAO dao = new SnippetDAO(logger);

        UUID uuid = Generators.timeBasedGenerator().generate();

        logger.log(uuid.toString());

        Gson gson = new Gson();

        Snippet exists = dao.getSnippet(uuid.toString());
        if(exists == null) {
            logger.log("In first case");
            Snippet snippet = Snippet.makeSnippet(uuid.toString(), text, info, password, codingLang);
            logger.log(snippet.getID());
            logger.log("Snippet: " + gson.toJson(snippet));
            return dao.addSnippet(snippet) ? uuid.toString() : null;
        } else {
        	logger.log("Snippet: " + gson.toJson(exists));
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
    public CreateSnippetResponse handleRequest(CreateSnippetRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        CreateSnippetResponse response;
        try {
            String uuid = createSnippet(input.getText(), input.getInfo(), input.getPassword(), input.getCodingLang());

            if(uuid != null) {
                response = CreateSnippetResponse.makeCreateSnippetResponse(uuid);
            } else {
                response = CreateSnippetResponse.makeCreateSnippetResponse("UUID is null", 442);
            }
        } catch (Exception e) {
            response = CreateSnippetResponse.makeCreateSnippetResponse("Unable to create snippet: " + "(" + e.getLocalizedMessage() + ")", 400);
        }

        return response;
    }
}
