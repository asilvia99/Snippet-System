package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.UpdateSnippetInfoRequest;
import edu.wpi.cs.calliope.snippetsystem.http.requests.UpdateSnippetPasswordRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.UpdateSnippetComponentResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UpdateSnippetPasswordHandlerTest {
    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    void testGoodInput(String incoming) throws IOException {
        UpdateSnippetPasswordHandler handler = new UpdateSnippetPasswordHandler();
        UpdateSnippetPasswordRequest request = new Gson().fromJson(incoming, UpdateSnippetPasswordRequest.class);

        UpdateSnippetComponentResponse response = handler.handleRequest(request, createContext("Update Snippet Component"));
        Assert.assertEquals(200, response.getHttpCode());
    }

    void testBadInput(String incoming) throws IOException {
        UpdateSnippetPasswordHandler handler = new UpdateSnippetPasswordHandler();
        UpdateSnippetPasswordRequest request = new Gson().fromJson(incoming, UpdateSnippetPasswordRequest.class);

        UpdateSnippetComponentResponse response = handler.handleRequest(request, createContext("Update Snippet Component"));
        Assert.assertTrue(400 <= response.getHttpCode());
    }

    @Test
    public void testUpdateHandler() {
        String uuid = "test_id";

        try {
            SnippetDAO dao = new SnippetDAO();

            Snippet snippet = Snippet.makeSnippet(uuid, null, "Info", null, null);

            dao.addSnippet(snippet);

            testGoodInput("{\"ID\": \"" + uuid + "\", \"password\": \"password\"}");

            dao.deleteSnippet(uuid);
        } catch (Exception e) {
            Assert.fail("Test Update Password failed");
        }
    }

    @Test
    public void testUpdateBadInput() {
        String uuid = "test_id";

        try {
            testBadInput("{\"ID\": \"" + uuid + "\", \"Password\": \"password\"}");
        } catch (Exception e) {
            Assert.fail("Test Bad Update Password failed");
        }
    }
}