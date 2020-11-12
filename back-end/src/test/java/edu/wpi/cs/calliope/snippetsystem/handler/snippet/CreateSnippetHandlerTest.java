package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CreateSnippetHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    String testGoodInput(Snippet incoming) throws IOException {
        CreateSnippetHandler handler = new CreateSnippetHandler();
        String incoming_json = new Gson().toJson(incoming);
        CreateSnippetRequest request = new Gson().fromJson(incoming_json, CreateSnippetRequest.class);

        CreateSnippetResponse response = handler.handleRequest(request, createContext("Create New Snippet"));
        Assert.assertNotNull(response.getResponse());
        Assert.assertEquals(200, response.getHttpCode());
        return response.getResponse();
    }

    void testBadInput(String incoming) throws IOException {
        CreateSnippetHandler handler = new CreateSnippetHandler();
        CreateSnippetRequest req = new Gson().fromJson(incoming, CreateSnippetRequest.class);

        CreateSnippetResponse response = handler.handleRequest(req, createContext("compute"));

        Assert.assertEquals(400, response.getHttpCode());
    }

    @Test
    public void testCreateNew() {
        Snippet snippet = Snippet.makeSnippet(null, "Some text", "Some info", null, null);

        try {
            String uuidString = testGoodInput(snippet);
            SnippetDAO dao = new SnippetDAO();
            dao.deleteSnippet(uuidString);
        } catch (Exception e) {
            Assert.fail("Test Create New failed");
        }
    }

    @Test
    public void testCreateNewWithPassword() {
        Snippet snippet = Snippet.makeSnippet(null, "Some text", "Some info", "password", null);

        try {
            String uuidString = testGoodInput(snippet);
            SnippetDAO dao = new SnippetDAO();
            dao.deleteSnippet(uuidString);
        } catch (Exception e) {
            Assert.fail("Test Create New failed");
        }
    }

    @Test
    public void testCreateNewFullRequest() {
        Snippet snippet = Snippet.makeSnippet(null, "Some text", "Some info", "password", "Java");

        try {
            String uuidString = testGoodInput(snippet);
            SnippetDAO dao = new SnippetDAO();
            dao.deleteSnippet(uuidString);
        } catch (Exception e) {
            Assert.fail("Test Create New failed");
        }
    }

    @Test
    public void testCreateNewBadInput() {
        String badInput = "{\"codingLang\": \"Java\"}";

        try {
            testBadInput(badInput);
        } catch (Exception e) {
            Assert.fail();
        }

    }
}