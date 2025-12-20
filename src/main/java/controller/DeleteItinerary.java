package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnection;

@WebServlet("/DeleteItinerary")
public class DeleteItinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain; charset=UTF-8");
        
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("s_log_u") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return;
            }

            String username = (String) session.getAttribute("s_log_u");
            String continent = request.getParameter("continent");
            String country = request.getParameter("country");
            String city = request.getParameter("city");

            System.out.println("Delete request: username=" + username + ", continent=" + continent + 
                             ", country=" + country + ", city=" + city);

            if (continent == null || country == null || city == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing parameters");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM users_itinerary WHERE username=? AND continent=? AND country=? AND city=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, continent);
                ps.setString(3, country);
                ps.setString(4, city);

                int rowsDeleted = ps.executeUpdate();
                
                System.out.println("Rows deleted: " + rowsDeleted);

                if (rowsDeleted > 0) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Success");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Itinerary not found");
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error in DeleteItinerary servlet:");
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}