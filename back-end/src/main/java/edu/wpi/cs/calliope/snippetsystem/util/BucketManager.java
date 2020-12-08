package edu.wpi.cs.calliope.snippetsystem.util;

public class BucketManager {

    private final String bucket = "cs509-snippetsystem";

    private final String folder = "password/";
    private final String test_folder = "test_password/";

    private String cachedResult = null;

    private static BucketManager instance = null;

    public String getPasswordFolder() {
        if (cachedResult != null) {
            return cachedResult;
        }

        String test = System.getenv("lambdatesting");

        if (test != null) {
            cachedResult = test_folder;
            System.out.println("Using Test");
        } else {
            cachedResult = folder;
        }

        return cachedResult;
    }

    public static BucketManager getInstance() {
        if (instance == null) {
            instance = new BucketManager();
        }

        return instance;
    }

    public String getBucket() {
        return bucket;
    }
}
