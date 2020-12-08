package edu.wpi.cs.calliope.snippetsystem.handler.admin;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.db.AdminDAO;
import edu.wpi.cs.calliope.snippetsystem.http.responses.GetSnippetSummariesResponse;
import edu.wpi.cs.calliope.snippetsystem.model.SnippetSummary;

import java.util.List;

public class ListSnippetsHandler implements RequestHandler<Object, GetSnippetSummariesResponse> {

    LambdaLogger logger;

    List<SnippetSummary> getSnippetSummaries() throws Exception {
        if (logger != null) {
            logger.log("In getSnippetSummaries");
        }
        AdminDAO dao = new AdminDAO();

        return dao.getSnippetSummaryList();
    }

    /**
     * Handles the request
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public GetSnippetSummariesResponse handleRequest(Object input, Context context) {
        logger = context.getLogger();

        GetSnippetSummariesResponse response;
        try {
            List<SnippetSummary> summaries = getSnippetSummaries();

            if (summaries != null) {
                Gson gson = new Gson();
                response = GetSnippetSummariesResponse.makeGetSnippetSummariesResponse(gson.toJson(summaries));
            } else {
                response = GetSnippetSummariesResponse.makeGetSnippetSummariesResponse("Unable to get list", 442);
            }
        } catch (Exception e) {
            response = GetSnippetSummariesResponse.makeGetSnippetSummariesResponse("Unable to get list: (" + e.getMessage() + ")", 400);
        }

        return response;
    }
}
