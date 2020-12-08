package edu.wpi.cs.calliope.snippetsystem.handler.admin;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.http.responses.GetSnippetSummariesResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ListSnippetsHandlerTest {
    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }


    // Credits: JeanValjean https://stackoverflow.com/questions/10174898/how-to-check-whether-a-given-string-is-valid-json-in-java
    boolean isJSONValid(String jsonInString) {
        Gson gson = new Gson();
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }

    //Checks that the response is 200 if info is put in properly
    void testSnippetSummary() throws IOException {
        ListSnippetsHandler handler = new ListSnippetsHandler();
        Object request = new Object();

        GetSnippetSummariesResponse response = handler.handleRequest(request, createContext("Snippet Summaries"));
        System.out.println(response.getResponse());

        Assert.assertEquals(200, response.getHttpCode());
        Assert.assertTrue(isJSONValid(response.getResponse()));
    }

    @Test
    public void testSnippetSummaryResponse() {
        try {
            testSnippetSummary();
        } catch (Exception e) {
            Assert.fail("Test Snippet Summary Response failed");
        }
    }
}