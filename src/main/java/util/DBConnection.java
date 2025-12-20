package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL =
		    "jdbc:mysql://gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/wandercraft_db"
		  + "?useSSL=true"
		  + "&serverTimezone=UTC"
		  + "&disableAbandonedConnectionCleanup=true";
    private static final String USERNAME = "2QLepttZVfHrrRZ.root";
    private static final String PASSWORD = "t2ZMtRiBehrh4SXj";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}