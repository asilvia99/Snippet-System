package edu.wpi.cs.calliope.snippetsystem.handler.snippet;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDGenerator;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.CreateSnippetRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.CreateSnippetResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

public class CreateSnippetHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    void testGoodInput(Snippet incoming, String outgoing) throws IOException {
        CreateSnippetHandler handler = new CreateSnippetHandler();
        String incoming_json = new Gson().toJson(incoming);
        CreateSnippetRequest request = new Gson().fromJson(incoming_json, CreateSnippetRequest.class);

        CreateSnippetResponse response = handler.handleRequest(request, createContext("Create New Snippet"));
        Assert.assertEquals(outgoing, response.getResponse());
        Assert.assertEquals(200, response.getHttpCode());
    }

    void testExistingInput(Snippet incoming) throws IOException {
        CreateSnippetHandler handler = new CreateSnippetHandler();
        String incoming_json = new Gson().toJson(incoming);
        CreateSnippetRequest request = new Gson().fromJson(incoming_json, CreateSnippetRequest.class);

        CreateSnippetResponse response = handler.handleRequest(request, createContext("Create New Snippet"));
        Assert.assertEquals(400, response.getHttpCode());
    }

    @Test
    public void testCreateNew() {
        UUID uuid = Generators.timeBasedGenerator().generate();

        String uuidString = uuid.toString();

        Snippet snippet = Snippet.makeSnippet(uuidString, "Some text", "Some info", null, null);

        try {
            testGoodInput(snippet, uuidString);
            SnippetDAO dao = new SnippetDAO();
            dao.deleteSnippet(uuidString);
        } catch (Exception e) {
            Assert.fail("Test Create New failed");
        }
    }

    @Test
    public void testCreateExisting() {

    }
}