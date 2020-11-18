package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.DeleteSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.requests.ViewSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.DeleteSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.http.responses.ViewSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ViewSnippetHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    void testGoodView(String incoming) throws IOException {
        ViewSnippetHandler handler = new ViewSnippetHandler();
        ViewSnippetRequest request = new Gson().fromJson(incoming, ViewSnippetRequest.class);

        ViewSnippetResponse response = handler.handleRequest(request, createContext("View Snippet"));
        Assert.assertEquals(200, response.getHttpCode());
    }

    void testBadInput(String incoming) throws IOException {
        ViewSnippetHandler handler = new ViewSnippetHandler();
        ViewSnippetRequest req = new Gson().fromJson(incoming, ViewSnippetRequest.class);

        ViewSnippetResponse response = handler.handleRequest(req, createContext("View Snippet"));

        Assert.assertTrue(400 <= response.getHttpCode());
    }

    @Test
    public void testViewHandler() {
        String uuid = "test_id";

        try {
            SnippetDAO dao = new SnippetDAO();

            Snippet snippet = Snippet.makeSnippet(uuid, null, "Info", null, null);

            dao.addSnippet(snippet);

            testGoodView("{\"ID\": \"" + uuid + "\"}");

            dao.deleteSnippet(uuid);
        } catch (Exception e) {
            Assert.fail("Test View failed");
        }
    }

    @Test
    public void testViewHandlerBadInput() {
        String uuid = "test_id";

        try {
            testBadInput("{\"id\": \"" + uuid + "\"}");
        } catch (Exception e) {
            Assert.fail("Test Bad View failed");
        }
    }

}