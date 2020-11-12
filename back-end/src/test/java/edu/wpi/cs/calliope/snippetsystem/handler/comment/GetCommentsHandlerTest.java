package edu.wpi.cs.calliope.snippetsystem.handler.comment;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.db.CommentDAO;
import edu.wpi.cs.calliope.snippetsystem.db.SnippetDAO;
import edu.wpi.cs.calliope.snippetsystem.http.requests.GetCommentsRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.GetCommentsResponse;
import edu.wpi.cs.calliope.snippetsystem.model.Comment;
import edu.wpi.cs.calliope.snippetsystem.model.Snippet;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class GetCommentsHandlerTest {

    Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    //Checks that the response is 200 if info is put in properly
    void testGoodInput(String incoming) throws IOException {
        GetCommentsHandler handler = new GetCommentsHandler();
        String incoming_json = new Gson().toJson(incoming);
        GetCommentsRequest request = new Gson().fromJson(incoming_json, GetCommentsRequest.class);

        GetCommentsResponse response = handler.handleRequest(request, createContext("Get Comments"));
        Assert.assertNotNull(response.getResponse());
        Assert.assertEquals(200, response.getHttpCode());
    }
    
    
    @Test
    public void testGetComments() {
        
    	//make a snippet with dummy id, add the comment to that snippet
    	String sID = "fakeSid";
    	Snippet snippet = Snippet.makeSnippet(sID, "text", "info", "password", null);
    	Comment comment = Comment.makeComment(null, sID, "This is the text", "1:1", "10:10");
    	Comment comment2 = Comment.makeComment(null, sID, "This is the other text", "2:2", "11:11");


        try {        
        	//add fake snippet to database
        	SnippetDAO sDAO = new SnippetDAO();
        	sDAO.addSnippet(snippet);
        	CommentDAO cDAO = new CommentDAO();
        	cDAO.addComment(comment);
        	cDAO.addComment(comment2);
        	
        	testGoodInput(sID);
        	
        	//use the snippetDAO to erase dummy snippet and it should delete the comments too
            sDAO.deleteSnippet(sID);
            
        } catch (Exception e) {
            Assert.fail("Test Get Comments failed");
        }
    }
    
    
}
