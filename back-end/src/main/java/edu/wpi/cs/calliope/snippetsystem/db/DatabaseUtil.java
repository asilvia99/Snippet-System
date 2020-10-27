package edu.wpi.cs.calliope.snippetsystem.db;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // DB user names and passwords (as well as the db endpoint) should never be stored directly in code.
    //
    // https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
    //
    // The above link shows how to ensure Lambda function has access to environment as well as local
    public final static String jdbcTag = "jdbc:mysql://";
    public final static String DB_PORT = "3306";
    public final static String multiQueries = "?allowMultiQueries=true";

    // Make sysEnv variable lambdaTesting so we know we are locally testing
    public final static String DB_NAME = "snippet_system";

    // pooled across all usages.
    static Connection conn;

    /**
     * Singleton access to DB connection to share resources effectively across multiple accesses.
     * @param logger
     */
    protected static Connection connect(LambdaLogger logger) throws Exception {
        if (conn != null) { return conn; }

        // this is resistant to any SQL-injection attack.
        String schemaName = DB_NAME;

        String db_user = System.getenv("DB_USER");
        if (db_user == null) {
            System.err.println("Environment variable DB_USER is not set!");
        }
        String db_passwd = System.getenv("DB_PASSWD");
        if (db_passwd == null) {
            System.err.println("Environment variable DB_PASSWD is not set!");
        }
        String db_host = System.getenv("DB_HOST");
        if (db_host == null) {
            System.err.println("Environment variable db_host is not set!");
        }

        logger.log("USER: " + db_user);
        logger.log("PASS: " + db_passwd);
        logger.log("HOST: " + db_host);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    jdbcTag + db_host + ":" + DB_PORT + "/" + schemaName + multiQueries,
                    db_user,
                    db_passwd);
            return conn;
        } catch (Exception e) {
            logger.log("DB-ERROR:" + schemaName + "," + db_user + "," + db_passwd + "," + db_host);
            throw new Exception("Failed in database connection");
        }
    }
}