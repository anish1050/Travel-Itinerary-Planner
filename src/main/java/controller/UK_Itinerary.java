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

@WebServlet("/UK_Itinerary")
public class UK_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String london_op1 = request.getParameter("london_op1");
        String london_op2 = request.getParameter("london_op2");
        String london_op3 = request.getParameter("london_op3");
        String london_op4 = request.getParameter("london_op4");
        
        String edinburgh_op1 = request.getParameter("edinburgh_op1");
        String edinburgh_op2 = request.getParameter("edinburgh_op2");
        String edinburgh_op3 = request.getParameter("edinburgh_op3");
        String edinburgh_op4 = request.getParameter("edinburgh_op4");
        
        String liverpool_op1 = request.getParameter("liverpool_op1");
        String liverpool_op2 = request.getParameter("liverpool_op2");
        String liverpool_op3 = request.getParameter("liverpool_op3");
        String liverpool_op4 = request.getParameter("liverpool_op4");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // LONDON SECTION - All combinations (2^4 - 1 = 15 combinations)
            if(cityname.equals("london")) {
                
                // Single selections (4 combinations)
                if(london_op1 != null && london_op2 == null && london_op3 == null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, london_op1, null, null, null, response);
                }
                else if(london_op1 == null && london_op2 != null && london_op3 == null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, null, london_op2, null, null, response);
                }
                else if(london_op1 == null && london_op2 == null && london_op3 != null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, null, null, london_op3, null, response);
                }
                else if(london_op1 == null && london_op2 == null && london_op3 == null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, null, null, null, london_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(london_op1 != null && london_op2 != null && london_op3 == null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, london_op1, london_op2, null, null, response);
                }
                else if(london_op1 != null && london_op2 == null && london_op3 != null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, london_op1, null, london_op3, null, response);
                }
                else if(london_op1 != null && london_op2 == null && london_op3 == null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, london_op1, null, null, london_op4, response);
                }
                else if(london_op1 == null && london_op2 != null && london_op3 != null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, null, london_op2, london_op3, null, response);
                }
                else if(london_op1 == null && london_op2 != null && london_op3 == null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, null, london_op2, null, london_op4, response);
                }
                else if(london_op1 == null && london_op2 == null && london_op3 != null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, null, null, london_op3, london_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(london_op1 != null && london_op2 != null && london_op3 != null && london_op4 == null) {
                    insertLondonItinerary(conn, log_username, london_op1, london_op2, london_op3, null, response);
                }
                else if(london_op1 != null && london_op2 != null && london_op3 == null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, london_op1, london_op2, null, london_op4, response);
                }
                else if(london_op1 != null && london_op2 == null && london_op3 != null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, london_op1, null, london_op3, london_op4, response);
                }
                else if(london_op1 == null && london_op2 != null && london_op3 != null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, null, london_op2, london_op3, london_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(london_op1 != null && london_op2 != null && london_op3 != null && london_op4 != null) {
                    insertLondonItinerary(conn, log_username, london_op1, london_op2, london_op3, london_op4, response);
                }
            }
            
            // EDINBURGH SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("edinburgh")) {
                
                // Single selections (4 combinations)
                if(edinburgh_op1 != null && edinburgh_op2 == null && edinburgh_op3 == null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, null, null, null, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 != null && edinburgh_op3 == null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, null, edinburgh_op2, null, null, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 == null && edinburgh_op3 != null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, null, null, edinburgh_op3, null, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 == null && edinburgh_op3 == null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, null, null, null, edinburgh_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(edinburgh_op1 != null && edinburgh_op2 != null && edinburgh_op3 == null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, edinburgh_op2, null, null, response);
                }
                else if(edinburgh_op1 != null && edinburgh_op2 == null && edinburgh_op3 != null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, null, edinburgh_op3, null, response);
                }
                else if(edinburgh_op1 != null && edinburgh_op2 == null && edinburgh_op3 == null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, null, null, edinburgh_op4, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 != null && edinburgh_op3 != null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, null, edinburgh_op2, edinburgh_op3, null, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 != null && edinburgh_op3 == null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, null, edinburgh_op2, null, edinburgh_op4, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 == null && edinburgh_op3 != null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, null, null, edinburgh_op3, edinburgh_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(edinburgh_op1 != null && edinburgh_op2 != null && edinburgh_op3 != null && edinburgh_op4 == null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, edinburgh_op2, edinburgh_op3, null, response);
                }
                else if(edinburgh_op1 != null && edinburgh_op2 != null && edinburgh_op3 == null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, edinburgh_op2, null, edinburgh_op4, response);
                }
                else if(edinburgh_op1 != null && edinburgh_op2 == null && edinburgh_op3 != null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, null, edinburgh_op3, edinburgh_op4, response);
                }
                else if(edinburgh_op1 == null && edinburgh_op2 != null && edinburgh_op3 != null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, null, edinburgh_op2, edinburgh_op3, edinburgh_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(edinburgh_op1 != null && edinburgh_op2 != null && edinburgh_op3 != null && edinburgh_op4 != null) {
                    insertEdinburghItinerary(conn, log_username, edinburgh_op1, edinburgh_op2, edinburgh_op3, edinburgh_op4, response);
                }
            }
            
            // LIVERPOOL SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("liverpool")) {
                
                // Single selections (4 combinations)
                if(liverpool_op1 != null && liverpool_op2 == null && liverpool_op3 == null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, null, null, null, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 != null && liverpool_op3 == null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, null, liverpool_op2, null, null, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 == null && liverpool_op3 != null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, null, null, liverpool_op3, null, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 == null && liverpool_op3 == null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, null, null, null, liverpool_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(liverpool_op1 != null && liverpool_op2 != null && liverpool_op3 == null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, liverpool_op2, null, null, response);
                }
                else if(liverpool_op1 != null && liverpool_op2 == null && liverpool_op3 != null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, null, liverpool_op3, null, response);
                }
                else if(liverpool_op1 != null && liverpool_op2 == null && liverpool_op3 == null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, null, null, liverpool_op4, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 != null && liverpool_op3 != null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, null, liverpool_op2, liverpool_op3, null, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 != null && liverpool_op3 == null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, null, liverpool_op2, null, liverpool_op4, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 == null && liverpool_op3 != null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, null, null, liverpool_op3, liverpool_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(liverpool_op1 != null && liverpool_op2 != null && liverpool_op3 != null && liverpool_op4 == null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, liverpool_op2, liverpool_op3, null, response);
                }
                else if(liverpool_op1 != null && liverpool_op2 != null && liverpool_op3 == null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, liverpool_op2, null, liverpool_op4, response);
                }
                else if(liverpool_op1 != null && liverpool_op2 == null && liverpool_op3 != null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, null, liverpool_op3, liverpool_op4, response);
                }
                else if(liverpool_op1 == null && liverpool_op2 != null && liverpool_op3 != null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, null, liverpool_op2, liverpool_op3, liverpool_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(liverpool_op1 != null && liverpool_op2 != null && liverpool_op3 != null && liverpool_op4 != null) {
                    insertLiverpoolItinerary(conn, log_username, liverpool_op1, liverpool_op2, liverpool_op3, liverpool_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for London insertions
    private void insertLondonItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "United Kingdom");
        ps.setString(4, "London");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'United Kingdom.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("London - Inserted Rows: " + rows);
    }
    
    // Helper method for Edinburgh insertions
    private void insertEdinburghItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "United Kingdom");
        ps.setString(4, "Edinburgh");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'United Kingdom.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Edinburgh - Inserted Rows: " + rows);
    }
    
    // Helper method for Liverpool insertions
    private void insertLiverpoolItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "United Kingdom");
        ps.setString(4, "Liverpool");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'United Kingdom.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Liverpool - Inserted Rows: " + rows);
    }
}