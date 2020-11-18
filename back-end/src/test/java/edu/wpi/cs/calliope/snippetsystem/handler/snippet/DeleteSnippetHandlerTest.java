package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.DeleteSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.DeleteSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DeleteSnippetHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    void testGoodDelete(String incoming) throws IOException {
        DeleteSnippetHandler handler = new DeleteSnippetHandler();
        DeleteSnippetRequest request = new Gson().fromJson(incoming, DeleteSnippetRequest.class);

        DeleteSnippetResponse response = handler.handleRequest(request, createContext("Delete Snippet"));
        Assert.assertEquals(request.getID(), response.getResponse());
        Assert.assertEquals(200, response.getHttpCode());
    }

    void testBadInput(String incoming) throws IOException {
        DeleteSnippetHandler handler = new DeleteSnippetHandler();
        DeleteSnippetRequest req = new Gson().fromJson(incoming, DeleteSnippetRequest.class);

        DeleteSnippetResponse response = handler.handleRequest(req, createContext("Delete Snippet"));

        Assert.assertTrue(400 <= response.getHttpCode());
    }

    @Test
    public void testDeleteHandler() {
        String uuid = "test_id";

        try {
            SnippetDAO dao = new SnippetDAO();
            dao.addSnippet(Snippet.makeSnippet(uuid, null, "Info", null, null));

            testGoodDelete("{\"ID\": \"" + uuid + "\"}");
        } catch (Exception e) {
            Assert.fail("Test Delete failed");
        }
    }

    @Test
    public void testDeleteHandlerBadInput() {
        String uuid = "test_id";

        try {
            testBadInput("{\"id\": \"" + uuid + "\"}");
        } catch (Exception e) {
            Assert.fail("Test Bad Delete failed");
        }
    }

}