package edu.wpi.cs.calliope.snippetsystem.handler.admin;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import edu.wpi.cs.calliope.snippetsystem.TestContext;
import edu.wpi.cs.calliope.snippetsystem.http.requests.AdminPasswordRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.AdminPasswordResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ConfirmAdminPWHandlerTest {

        Context createContext(String apiCall) {
            TestContext ctx = new TestContext();
            ctx.setFunctionName(apiCall);
            return ctx;
        }

        //Checks that the response is 200 if info is put in properly
        void testCorrectPassword(String incoming) throws IOException {
            ConfirmAdminPWHandler handler = new ConfirmAdminPWHandler();
            AdminPasswordRequest request = new Gson().fromJson(incoming, AdminPasswordRequest.class);

            AdminPasswordResponse response = handler.handleRequest(request, createContext("Check PW"));
            Assert.assertEquals("Success", response.getResponse());
            Assert.assertEquals(200, response.getHttpCode());
        }

        //Checks that the response is 4** if info is put in properly
        void testIncorrectPassword(String incoming) throws IOException {
            ConfirmAdminPWHandler handler = new ConfirmAdminPWHandler();
            AdminPasswordRequest request = new Gson().fromJson(incoming, AdminPasswordRequest.class);

            AdminPasswordResponse response = handler.handleRequest(request, createContext("Check PW"));
            Assert.assertEquals("Fail", response.getResponse());
        }

        @Test
        public void testCorrectPassword() {
            String password = "password";

            try {
                testCorrectPassword("{\"password\": \"" + password + "\"}");
            } catch (Exception e) {
                Assert.fail("Test Check Password failed");
            }
        }

        @Test
        public void testBadPassword() {
        	String password = "this_is_not_the_password";

            try {
                testIncorrectPassword("{\"password\": \"" + password + "\"}");
            } catch (Exception e) {
                Assert.fail("Test Check Password failed");
            }
        }
}