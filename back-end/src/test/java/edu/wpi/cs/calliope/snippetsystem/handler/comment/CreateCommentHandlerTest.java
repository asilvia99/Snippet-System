package edu.wpi.cs.calliope.snippetsystem.handler.comment;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.CreateCommentRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.CreateCommentResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CreateCommentHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    //Checks that the response is 200 if info is put in properly
    void testGoodInput(Comment incoming) throws IOException {
        CreateCommentHandler handler = new CreateCommentHandler();
        String incoming_json = new Gson().toJson(incoming);
        CreateCommentRequest request = new Gson().fromJson(incoming_json, CreateCommentRequest.class);

        CreateCommentResponse response = handler.handleRequest(request, createContext("Create New Comment"));
        Assert.assertNotNull(response.getResponse());
        Assert.assertEquals(200, response.getHttpCode());
    }
    //Checks that the response is 400 if info is incorrect
    void testBadInput(Comment incoming) throws IOException {
        CreateCommentHandler handler = new CreateCommentHandler();
        String incoming_json = new Gson().toJson(incoming);
        CreateCommentRequest request = new Gson().fromJson(incoming_json, CreateCommentRequest.class);

        CreateCommentResponse response = handler.handleRequest(request, createContext("Create New Comment"));
        Assert.assertNotNull(response.getResponse());
        Assert.assertEquals(400, response.getHttpCode());
    }

    @Test
    public void testCreateNew() {
        
    	//make a snippet with dummy id, add the comment to that snippet
    	String sID = "fakeSid";
    	Snippet snippet = Snippet.makeSnippet(sID, "text", "info", "password", null);
    	Comment comment = Comment.makeComment(null, sID, "This is the text", "12:2", "15:10");

        try {        
        	//add fake snippet to database
        	SnippetDAO sDAO = new SnippetDAO();
        	sDAO.addSnippet(snippet);
        	
        	testGoodInput(comment);
        	
        	//use the snippetDAO to erase dummy snippet and it should delete the comments too
            sDAO.deleteSnippet(sID);
            
        } catch (Exception e) {
            Assert.fail("Test Create New Comment failed");
        }
    }

    @Test
    public void testNonExistingSnippet() {
    	Comment comment = Comment.makeComment(null, "nonexistingSID", "This is the text", "12:2", "15:10");

        try {        
        	
        	testBadInput(comment);

            
        } catch (Exception e) {
            Assert.fail("Test Create Bad Comment failed");
        }
    }
}