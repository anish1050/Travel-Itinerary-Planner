package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String host = System.getenv("DB_HOST");
            String port = System.getenv("DB_PORT");
            String db   = System.getenv("DB_NAME");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                         "?useSSL=true&requireSSL=true&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}