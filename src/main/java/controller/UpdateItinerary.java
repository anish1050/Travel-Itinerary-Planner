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

@WebServlet("/UpdateItinerary")
public class UpdateItinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("s_log_u") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String username = (String) session.getAttribute("s_log_u");
            String continent = request.getParameter("continent");
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String activity1 = request.getParameter("activity1");
            String activity2 = request.getParameter("activity2");
            String activity3 = request.getParameter("activity3");
            String activity4 = request.getParameter("activity4");

            System.out.println("Update request: username=" + username + ", continent=" + continent + 
                             ", country=" + country + ", city=" + city);

            if (continent == null || country == null || city == null) {
                response.sendRedirect("InitialUserLoginProfile");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "UPDATE users_itinerary SET activity1=?, activity2=?, activity3=?, activity4=? " +
                           "WHERE username=? AND continent=? AND country=? AND city=?";
                
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, activity1 != null ? activity1 : "");
                ps.setString(2, activity2 != null ? activity2 : "");
                ps.setString(3, activity3 != null ? activity3 : "");
                ps.setString(4, activity4 != null ? activity4 : "");
                ps.setString(5, username);
                ps.setString(6, continent);
                ps.setString(7, country);
                ps.setString(8, city);

                int rowsUpdated = ps.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);

                response.sendRedirect("InitialUserLoginProfile");
            }

        } catch (Exception e) {
            System.err.println("Error in UpdateItinerary servlet:");
            e.printStackTrace();
            response.sendRedirect("InitialUserLoginProfile");
        }
    }
}