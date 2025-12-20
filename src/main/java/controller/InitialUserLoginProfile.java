package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

@WebServlet("/InitialUserLoginProfile")
public class InitialUserLoginProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("s_log_u") == null) {
            response.sendRedirect("LoginPage.html");
            return;
        }

        String username = (String) session.getAttribute("s_log_u");

        List<Map<String, String>> itineraries = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users_itinerary WHERE username = ?"
            );
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("continent", rs.getString("continent"));
                row.put("country", rs.getString("country"));
                row.put("city", rs.getString("city"));
                row.put("activity1", rs.getString("activity1"));
                row.put("activity2", rs.getString("activity2"));
                row.put("activity3", rs.getString("activity3"));
                row.put("activity4", rs.getString("activity4"));
                itineraries.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send data to JSP
        request.setAttribute("itineraries", itineraries);
        request.getRequestDispatcher("InitialUserLoginProfile.jsp")
               .forward(request, response);
    }
}
