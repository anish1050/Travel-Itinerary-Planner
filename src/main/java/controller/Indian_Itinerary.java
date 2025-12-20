package controller;

import java.io.IOException;
import util.ItineraryLimitManager;
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

@WebServlet("/Indian_Itinerary")
public class Indian_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String mumbai_op1 = request.getParameter("mumbai_op1");
        String mumbai_op2 = request.getParameter("mumbai_op2");
        String mumbai_op3 = request.getParameter("mumbai_op3");
        
        String delhi_op1 = request.getParameter("delhi_op1");
        String delhi_op2 = request.getParameter("delhi_op2");
        String delhi_op3 = request.getParameter("delhi_op3");
        String delhi_op4 = request.getParameter("delhi_op4");
        
        String agra_op1 = request.getParameter("agra_op1");
        String agra_op2 = request.getParameter("agra_op2");
        String agra_op3 = request.getParameter("agra_op3");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // MUMBAI SECTION - All combinations (2^3 - 1 = 7 combinations)
            if(cityname.equals("mumbai")) {
                
                // Single selections (3 combinations)
                if(mumbai_op1 != null && mumbai_op2 == null && mumbai_op3 == null) {
                    insertMumbaiItinerary(conn, log_username, mumbai_op1, null, null, response);
                }
                else if(mumbai_op1 == null && mumbai_op2 != null && mumbai_op3 == null) {
                    insertMumbaiItinerary(conn, log_username, null, mumbai_op2, null, response);
                }
                else if(mumbai_op1 == null && mumbai_op2 == null && mumbai_op3 != null) {
                    insertMumbaiItinerary(conn, log_username, null, null, mumbai_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(mumbai_op1 != null && mumbai_op2 != null && mumbai_op3 == null) {
                    insertMumbaiItinerary(conn, log_username, mumbai_op1, mumbai_op2, null, response);
                }
                else if(mumbai_op1 != null && mumbai_op2 == null && mumbai_op3 != null) {
                    insertMumbaiItinerary(conn, log_username, mumbai_op1, null, mumbai_op3, response);
                }
                else if(mumbai_op1 == null && mumbai_op2 != null && mumbai_op3 != null) {
                    insertMumbaiItinerary(conn, log_username, null, mumbai_op2, mumbai_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(mumbai_op1 != null && mumbai_op2 != null && mumbai_op3 != null) {
                    insertMumbaiItinerary(conn, log_username, mumbai_op1, mumbai_op2, mumbai_op3, response);
                }
            }
            
            // DELHI SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("delhi")) {
                
                // Single selections (4 combinations)
                if(delhi_op1 != null && delhi_op2 == null && delhi_op3 == null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, null, null, null, response);
                }
                else if(delhi_op1 == null && delhi_op2 != null && delhi_op3 == null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, null, delhi_op2, null, null, response);
                }
                else if(delhi_op1 == null && delhi_op2 == null && delhi_op3 != null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, null, null, delhi_op3, null, response);
                }
                else if(delhi_op1 == null && delhi_op2 == null && delhi_op3 == null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, null, null, null, delhi_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(delhi_op1 != null && delhi_op2 != null && delhi_op3 == null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, delhi_op2, null, null, response);
                }
                else if(delhi_op1 != null && delhi_op2 == null && delhi_op3 != null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, null, delhi_op3, null, response);
                }
                else if(delhi_op1 != null && delhi_op2 == null && delhi_op3 == null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, null, null, delhi_op4, response);
                }
                else if(delhi_op1 == null && delhi_op2 != null && delhi_op3 != null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, null, delhi_op2, delhi_op3, null, response);
                }
                else if(delhi_op1 == null && delhi_op2 != null && delhi_op3 == null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, null, delhi_op2, null, delhi_op4, response);
                }
                else if(delhi_op1 == null && delhi_op2 == null && delhi_op3 != null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, null, null, delhi_op3, delhi_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(delhi_op1 != null && delhi_op2 != null && delhi_op3 != null && delhi_op4 == null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, delhi_op2, delhi_op3, null, response);
                }
                else if(delhi_op1 != null && delhi_op2 != null && delhi_op3 == null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, delhi_op2, null, delhi_op4, response);
                }
                else if(delhi_op1 != null && delhi_op2 == null && delhi_op3 != null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, null, delhi_op3, delhi_op4, response);
                }
                else if(delhi_op1 == null && delhi_op2 != null && delhi_op3 != null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, null, delhi_op2, delhi_op3, delhi_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(delhi_op1 != null && delhi_op2 != null && delhi_op3 != null && delhi_op4 != null) {
                    insertDelhiItinerary(conn, log_username, delhi_op1, delhi_op2, delhi_op3, delhi_op4, response);
                }
            }
            
            // AGRA SECTION - All combinations (2^3 - 1 = 7 combinations)
            else if(cityname.equals("agra")) {
                
                // Single selections (3 combinations)
                if(agra_op1 != null && agra_op2 == null && agra_op3 == null) {
                    insertAgraItinerary(conn, log_username, agra_op1, null, null, response);
                }
                else if(agra_op1 == null && agra_op2 != null && agra_op3 == null) {
                    insertAgraItinerary(conn, log_username, null, agra_op2, null, response);
                }
                else if(agra_op1 == null && agra_op2 == null && agra_op3 != null) {
                    insertAgraItinerary(conn, log_username, null, null, agra_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(agra_op1 != null && agra_op2 != null && agra_op3 == null) {
                    insertAgraItinerary(conn, log_username, agra_op1, agra_op2, null, response);
                }
                else if(agra_op1 != null && agra_op2 == null && agra_op3 != null) {
                    insertAgraItinerary(conn, log_username, agra_op1, null, agra_op3, response);
                }
                else if(agra_op1 == null && agra_op2 != null && agra_op3 != null) {
                    insertAgraItinerary(conn, log_username, null, agra_op2, agra_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(agra_op1 != null && agra_op2 != null && agra_op3 != null) {
                    insertAgraItinerary(conn, log_username, agra_op1, agra_op2, agra_op3, response);
                }
            }
            
            ItineraryLimitManager.enforceLimit(conn, log_username);
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Mumbai insertions
    private void insertMumbaiItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
    	
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "India");
        ps.setString(4, "Mumbai");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'India.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Mumbai - Inserted Rows: " + rows);
    }
    
    // Helper method for Delhi insertions
    private void insertDelhiItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "India");
        ps.setString(4, "Delhi");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'India.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Delhi - Inserted Rows: " + rows);
    }
    
    // Helper method for Agra insertions
    private void insertAgraItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "India");
        ps.setString(4, "Agra");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'India.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Agra - Inserted Rows: " + rows);
    }
    
}