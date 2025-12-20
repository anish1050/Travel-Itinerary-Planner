package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItineraryLimitManager {

    private static final int MAX_ITINERARIES = 10;

    public static void enforceLimit(Connection conn, String username) throws Exception {

        PreparedStatement countStmt = conn.prepareStatement(
            "SELECT COUNT(*) FROM users_itinerary WHERE username = ?"
        );
        countStmt.setString(1, username);
        ResultSet rs = countStmt.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        if (count > MAX_ITINERARIES) {
            PreparedStatement deleteStmt = conn.prepareStatement(
                "DELETE FROM users_itinerary " +
                "WHERE username = ? " +
                "ORDER BY created_at ASC " +
                "LIMIT 1"
            );
            deleteStmt.setString(1, username);
            deleteStmt.executeUpdate();
        }
    }
}