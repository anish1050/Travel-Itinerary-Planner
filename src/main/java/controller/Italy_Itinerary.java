package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

@WebServlet("/Italy_Itinerary")
public class Italy_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String rome_op1 = request.getParameter("rome_op1");
        String rome_op2 = request.getParameter("rome_op2");
        String rome_op3 = request.getParameter("rome_op3");
        String rome_op4 = request.getParameter("rome_op4");
        String rome_op5 = request.getParameter("rome_op5");
        String rome_op6 = request.getParameter("rome_op6");
        String rome_op7 = request.getParameter("rome_op7");
        
        String florence_op1 = request.getParameter("florence_op1");
        String florence_op2 = request.getParameter("florence_op2");
        String florence_op3 = request.getParameter("florence_op3");
        String florence_op4 = request.getParameter("florence_op4");
        String florence_op5 = request.getParameter("florence_op5");
        String florence_op6 = request.getParameter("florence_op6");
        
        String venice_op1 = request.getParameter("venice_op1");
        String venice_op2 = request.getParameter("venice_op2");
        String venice_op3 = request.getParameter("venice_op3");
        String venice_op4 = request.getParameter("venice_op4");
        String venice_op5 = request.getParameter("venice_op5");
        String venice_op6 = request.getParameter("venice_op6");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // ROME SECTION - Handle all combinations for 7 activities
            if(cityname.equals("rome")) {
                insertRomeItinerary(conn, log_username, rome_op1, rome_op2, rome_op3, rome_op4, rome_op5, rome_op6, rome_op7, response);
            }
            
            // FLORENCE SECTION - Handle all combinations for 6 activities
            else if(cityname.equals("florence")) {
                insertFlorenceItinerary(conn, log_username, florence_op1, florence_op2, florence_op3, florence_op4, florence_op5, florence_op6, response);
            }
            
            // VENICE SECTION - Handle all combinations for 6 activities
            else if(cityname.equals("venice")) {
                insertVeniceItinerary(conn, log_username, venice_op1, venice_op2, venice_op3, venice_op4, venice_op5, venice_op6, response);
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Rome insertions
    private void insertRomeItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, String op5, String op6, String op7, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4, activity5, activity6, activity7) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Italy");
        ps.setString(4, "Rome");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        ps.setString(9, op5);
        ps.setString(10, op6);
        ps.setString(11, op7);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Rome Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Italy.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Rome - Inserted Rows: " + rows);
    }
    
    // Helper method for Florence insertions
    private void insertFlorenceItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, String op5, String op6, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4, activity5, activity6) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Italy");
        ps.setString(4, "Florence");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        ps.setString(9, op5);
        ps.setString(10, op6);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Florence Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Italy.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Florence - Inserted Rows: " + rows);
    }
    
    // Helper method for Venice insertions
    private void insertVeniceItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, String op5, String op6, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4, activity5, activity6) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Italy");
        ps.setString(4, "Venice");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        ps.setString(9, op5);
        ps.setString(10, op6);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Venice Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Italy.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Venice - Inserted Rows: " + rows);
    }
}