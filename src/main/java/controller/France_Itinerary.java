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

@WebServlet("/France_Itinerary")
public class France_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String paris_op1 = request.getParameter("paris_op1");
        String paris_op2 = request.getParameter("paris_op2");
        String paris_op3 = request.getParameter("paris_op3");
        String paris_op4 = request.getParameter("paris_op4");
        
        String versailles_op1 = request.getParameter("versailles_op1");
        String versailles_op2 = request.getParameter("versailles_op2");
        String versailles_op3 = request.getParameter("versailles_op3");
        
        String loire_op1 = request.getParameter("loire_op1");
        String loire_op2 = request.getParameter("loire_op2");
        String loire_op3 = request.getParameter("loire_op3");
        String loire_op4 = request.getParameter("loire_op4");
        
        String lyon_op1 = request.getParameter("lyon_op1");
        String lyon_op2 = request.getParameter("lyon_op2");
        String lyon_op3 = request.getParameter("lyon_op3");
        String lyon_op4 = request.getParameter("lyon_op4");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // PARIS SECTION - All combinations (2^4 - 1 = 15 combinations)
            if(cityname.equals("paris")) {
                
                // Single selections (4 combinations)
                if(paris_op1 != null && paris_op2 == null && paris_op3 == null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, paris_op1, null, null, null, response);
                }
                else if(paris_op1 == null && paris_op2 != null && paris_op3 == null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, null, paris_op2, null, null, response);
                }
                else if(paris_op1 == null && paris_op2 == null && paris_op3 != null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, null, null, paris_op3, null, response);
                }
                else if(paris_op1 == null && paris_op2 == null && paris_op3 == null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, null, null, null, paris_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(paris_op1 != null && paris_op2 != null && paris_op3 == null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, paris_op1, paris_op2, null, null, response);
                }
                else if(paris_op1 != null && paris_op2 == null && paris_op3 != null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, paris_op1, null, paris_op3, null, response);
                }
                else if(paris_op1 != null && paris_op2 == null && paris_op3 == null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, paris_op1, null, null, paris_op4, response);
                }
                else if(paris_op1 == null && paris_op2 != null && paris_op3 != null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, null, paris_op2, paris_op3, null, response);
                }
                else if(paris_op1 == null && paris_op2 != null && paris_op3 == null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, null, paris_op2, null, paris_op4, response);
                }
                else if(paris_op1 == null && paris_op2 == null && paris_op3 != null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, null, null, paris_op3, paris_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(paris_op1 != null && paris_op2 != null && paris_op3 != null && paris_op4 == null) {
                    insertParisItinerary(conn, log_username, paris_op1, paris_op2, paris_op3, null, response);
                }
                else if(paris_op1 != null && paris_op2 != null && paris_op3 == null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, paris_op1, paris_op2, null, paris_op4, response);
                }
                else if(paris_op1 != null && paris_op2 == null && paris_op3 != null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, paris_op1, null, paris_op3, paris_op4, response);
                }
                else if(paris_op1 == null && paris_op2 != null && paris_op3 != null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, null, paris_op2, paris_op3, paris_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(paris_op1 != null && paris_op2 != null && paris_op3 != null && paris_op4 != null) {
                    insertParisItinerary(conn, log_username, paris_op1, paris_op2, paris_op3, paris_op4, response);
                }
            }
            
            // VERSAILLES SECTION - All combinations (2^3 - 1 = 7 combinations)
            else if(cityname.equals("versailles")) {
                
                // Single selections (3 combinations)
                if(versailles_op1 != null && versailles_op2 == null && versailles_op3 == null) {
                    insertVersaillesItinerary(conn, log_username, versailles_op1, null, null, response);
                }
                else if(versailles_op1 == null && versailles_op2 != null && versailles_op3 == null) {
                    insertVersaillesItinerary(conn, log_username, null, versailles_op2, null, response);
                }
                else if(versailles_op1 == null && versailles_op2 == null && versailles_op3 != null) {
                    insertVersaillesItinerary(conn, log_username, null, null, versailles_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(versailles_op1 != null && versailles_op2 != null && versailles_op3 == null) {
                    insertVersaillesItinerary(conn, log_username, versailles_op1, versailles_op2, null, response);
                }
                else if(versailles_op1 != null && versailles_op2 == null && versailles_op3 != null) {
                    insertVersaillesItinerary(conn, log_username, versailles_op1, null, versailles_op3, response);
                }
                else if(versailles_op1 == null && versailles_op2 != null && versailles_op3 != null) {
                    insertVersaillesItinerary(conn, log_username, null, versailles_op2, versailles_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(versailles_op1 != null && versailles_op2 != null && versailles_op3 != null) {
                    insertVersaillesItinerary(conn, log_username, versailles_op1, versailles_op2, versailles_op3, response);
                }
            }
            
            // LOIRE VALLEY SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("loire")) {
                
                // Single selections (4 combinations)
                if(loire_op1 != null && loire_op2 == null && loire_op3 == null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, loire_op1, null, null, null, response);
                }
                else if(loire_op1 == null && loire_op2 != null && loire_op3 == null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, null, loire_op2, null, null, response);
                }
                else if(loire_op1 == null && loire_op2 == null && loire_op3 != null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, null, null, loire_op3, null, response);
                }
                else if(loire_op1 == null && loire_op2 == null && loire_op3 == null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, null, null, null, loire_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(loire_op1 != null && loire_op2 != null && loire_op3 == null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, loire_op1, loire_op2, null, null, response);
                }
                else if(loire_op1 != null && loire_op2 == null && loire_op3 != null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, loire_op1, null, loire_op3, null, response);
                }
                else if(loire_op1 != null && loire_op2 == null && loire_op3 == null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, loire_op1, null, null, loire_op4, response);
                }
                else if(loire_op1 == null && loire_op2 != null && loire_op3 != null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, null, loire_op2, loire_op3, null, response);
                }
                else if(loire_op1 == null && loire_op2 != null && loire_op3 == null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, null, loire_op2, null, loire_op4, response);
                }
                else if(loire_op1 == null && loire_op2 == null && loire_op3 != null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, null, null, loire_op3, loire_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(loire_op1 != null && loire_op2 != null && loire_op3 != null && loire_op4 == null) {
                    insertLoireItinerary(conn, log_username, loire_op1, loire_op2, loire_op3, null, response);
                }
                else if(loire_op1 != null && loire_op2 != null && loire_op3 == null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, loire_op1, loire_op2, null, loire_op4, response);
                }
                else if(loire_op1 != null && loire_op2 == null && loire_op3 != null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, loire_op1, null, loire_op3, loire_op4, response);
                }
                else if(loire_op1 == null && loire_op2 != null && loire_op3 != null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, null, loire_op2, loire_op3, loire_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(loire_op1 != null && loire_op2 != null && loire_op3 != null && loire_op4 != null) {
                    insertLoireItinerary(conn, log_username, loire_op1, loire_op2, loire_op3, loire_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Paris insertions
    private void insertParisItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "France");
        ps.setString(4, "Paris");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'France.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Paris - Inserted Rows: " + rows);
    }
    
    // Helper method for Versailles insertions
    private void insertVersaillesItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "France");
        ps.setString(4, "Versailles");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'France.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Versailles - Inserted Rows: " + rows);
    }
    
    // Helper method for Loire Valley insertions
    private void insertLoireItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "France");
        ps.setString(4, "Loire Valley");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'France.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Loire Valley - Inserted Rows: " + rows);
    }
    
    // Helper method for Lyon insertions
    private void insertLyonItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "France");
        ps.setString(4, "Lyon");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'France.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Lyon - Inserted Rows: " + rows);
    }
}