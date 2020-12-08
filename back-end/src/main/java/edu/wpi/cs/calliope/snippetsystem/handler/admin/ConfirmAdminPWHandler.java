package edu.wpi.cs.calliope.snippetsystem.handler.admin;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import edu.wpi.cs.calliope.snippetsystem.http.requests.AdminPasswordRequest;
import edu.wpi.cs.calliope.snippetsystem.http.responses.AdminPasswordResponse;
import edu.wpi.cs.calliope.snippetsystem.util.BucketManager;

import java.util.List;
import java.util.Scanner;

public class ConfirmAdminPWHandler implements RequestHandler<AdminPasswordRequest, AdminPasswordResponse> {

    private LambdaLogger logger;

    private AmazonS3 s3 = null;

    private String getAdminPassword() {
        if (logger != null) {
            logger.log("In getAdminPassword");
        }

        if (s3 == null) {
            logger.log("Attach to S3 request");
            s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
            logger.log("Attach to S3 succeed");
        }

        String password = null;

        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(BucketManager.getInstance().getBucket())
                .withPrefix(BucketManager.getInstance().getPasswordFolder());

        ListObjectsV2Result result = s3.listObjectsV2(request);

        List<S3ObjectSummary> objects = result.getObjectSummaries();

        for (S3ObjectSummary os: objects) {
            String name = os.getKey();
            logger.log("S3 found:" + name);

            if (name.endsWith("/")) { continue; }

            S3Object obj = s3.getObject(BucketManager.getInstance().getBucket(), name);

            try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
                Scanner sc = new Scanner(constantStream);
                String val = sc.nextLine();
                sc.close();

                password = val;
                break;
            } catch (Exception e) {
                logger.log("Unable to parse contents of " + name);
            }
        }

        return password;
    }


    @Override
    public AdminPasswordResponse handleRequest(AdminPasswordRequest input, Context context) {
        logger = context.getLogger();
        logger.log(input.toString());

        AdminPasswordResponse response;
        try {
            String password = getAdminPassword();

            if(password != null) {
                response = AdminPasswordResponse.makeAdminPasswordResponse(password.equals(input.getPassword()) ? "Success" : "Fail");
            } else {
                response = AdminPasswordResponse.makeAdminPasswordResponse("Password is null", 442);
            }
        } catch (Exception e) {
            response = AdminPasswordResponse.makeAdminPasswordResponse("Unable to retrieve password: " + "(" + e.getLocalizedMessage() + ")", 400);
        }

        return response;
    }
}
