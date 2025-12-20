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

@WebServlet("/Switzerland_Itinerary")
public class Switzerland_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String zurich_op1 = request.getParameter("zurich_op1");
        String zurich_op2 = request.getParameter("zurich_op2");
        String zurich_op3 = request.getParameter("zurich_op3");
        String zurich_op4 = request.getParameter("zurich_op4");
        
        String lucerne_op1 = request.getParameter("lucerne_op1");
        String lucerne_op2 = request.getParameter("lucerne_op2");
        String lucerne_op3 = request.getParameter("lucerne_op3");
        String lucerne_op4 = request.getParameter("lucerne_op4");
        
        String interlaken_op1 = request.getParameter("interlaken_op1");
        String interlaken_op2 = request.getParameter("interlaken_op2");
        String interlaken_op3 = request.getParameter("interlaken_op3");
        String interlaken_op4 = request.getParameter("interlaken_op4");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // ZURICH SECTION - All combinations (2^4 - 1 = 15 combinations)
            if(cityname.equals("zurich")) {
                
                // Single selections (4 combinations)
                if(zurich_op1 != null && zurich_op2 == null && zurich_op3 == null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, null, null, null, response);
                }
                else if(zurich_op1 == null && zurich_op2 != null && zurich_op3 == null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, null, zurich_op2, null, null, response);
                }
                else if(zurich_op1 == null && zurich_op2 == null && zurich_op3 != null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, null, null, zurich_op3, null, response);
                }
                else if(zurich_op1 == null && zurich_op2 == null && zurich_op3 == null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, null, null, null, zurich_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(zurich_op1 != null && zurich_op2 != null && zurich_op3 == null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, zurich_op2, null, null, response);
                }
                else if(zurich_op1 != null && zurich_op2 == null && zurich_op3 != null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, null, zurich_op3, null, response);
                }
                else if(zurich_op1 != null && zurich_op2 == null && zurich_op3 == null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, null, null, zurich_op4, response);
                }
                else if(zurich_op1 == null && zurich_op2 != null && zurich_op3 != null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, null, zurich_op2, zurich_op3, null, response);
                }
                else if(zurich_op1 == null && zurich_op2 != null && zurich_op3 == null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, null, zurich_op2, null, zurich_op4, response);
                }
                else if(zurich_op1 == null && zurich_op2 == null && zurich_op3 != null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, null, null, zurich_op3, zurich_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(zurich_op1 != null && zurich_op2 != null && zurich_op3 != null && zurich_op4 == null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, zurich_op2, zurich_op3, null, response);
                }
                else if(zurich_op1 != null && zurich_op2 != null && zurich_op3 == null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, zurich_op2, null, zurich_op4, response);
                }
                else if(zurich_op1 != null && zurich_op2 == null && zurich_op3 != null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, null, zurich_op3, zurich_op4, response);
                }
                else if(zurich_op1 == null && zurich_op2 != null && zurich_op3 != null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, null, zurich_op2, zurich_op3, zurich_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(zurich_op1 != null && zurich_op2 != null && zurich_op3 != null && zurich_op4 != null) {
                    insertZurichItinerary(conn, log_username, zurich_op1, zurich_op2, zurich_op3, zurich_op4, response);
                }
            }
            
            // LUCERNE SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("lucerne")) {
                
                // Single selections (4 combinations)
                if(lucerne_op1 != null && lucerne_op2 == null && lucerne_op3 == null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, null, null, null, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 != null && lucerne_op3 == null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, null, lucerne_op2, null, null, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 == null && lucerne_op3 != null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, null, null, lucerne_op3, null, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 == null && lucerne_op3 == null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, null, null, null, lucerne_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(lucerne_op1 != null && lucerne_op2 != null && lucerne_op3 == null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, lucerne_op2, null, null, response);
                }
                else if(lucerne_op1 != null && lucerne_op2 == null && lucerne_op3 != null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, null, lucerne_op3, null, response);
                }
                else if(lucerne_op1 != null && lucerne_op2 == null && lucerne_op3 == null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, null, null, lucerne_op4, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 != null && lucerne_op3 != null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, null, lucerne_op2, lucerne_op3, null, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 != null && lucerne_op3 == null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, null, lucerne_op2, null, lucerne_op4, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 == null && lucerne_op3 != null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, null, null, lucerne_op3, lucerne_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(lucerne_op1 != null && lucerne_op2 != null && lucerne_op3 != null && lucerne_op4 == null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, lucerne_op2, lucerne_op3, null, response);
                }
                else if(lucerne_op1 != null && lucerne_op2 != null && lucerne_op3 == null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, lucerne_op2, null, lucerne_op4, response);
                }
                else if(lucerne_op1 != null && lucerne_op2 == null && lucerne_op3 != null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, null, lucerne_op3, lucerne_op4, response);
                }
                else if(lucerne_op1 == null && lucerne_op2 != null && lucerne_op3 != null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, null, lucerne_op2, lucerne_op3, lucerne_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(lucerne_op1 != null && lucerne_op2 != null && lucerne_op3 != null && lucerne_op4 != null) {
                    insertLucerneItinerary(conn, log_username, lucerne_op1, lucerne_op2, lucerne_op3, lucerne_op4, response);
                }
            }
            
            // INTERLAKEN SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("interlaken")) {
                
                // Single selections (4 combinations)
                if(interlaken_op1 != null && interlaken_op2 == null && interlaken_op3 == null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, null, null, null, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 != null && interlaken_op3 == null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, null, interlaken_op2, null, null, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 == null && interlaken_op3 != null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, null, null, interlaken_op3, null, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 == null && interlaken_op3 == null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, null, null, null, interlaken_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(interlaken_op1 != null && interlaken_op2 != null && interlaken_op3 == null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, interlaken_op2, null, null, response);
                }
                else if(interlaken_op1 != null && interlaken_op2 == null && interlaken_op3 != null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, null, interlaken_op3, null, response);
                }
                else if(interlaken_op1 != null && interlaken_op2 == null && interlaken_op3 == null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, null, null, interlaken_op4, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 != null && interlaken_op3 != null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, null, interlaken_op2, interlaken_op3, null, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 != null && interlaken_op3 == null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, null, interlaken_op2, null, interlaken_op4, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 == null && interlaken_op3 != null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, null, null, interlaken_op3, interlaken_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(interlaken_op1 != null && interlaken_op2 != null && interlaken_op3 != null && interlaken_op4 == null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, interlaken_op2, interlaken_op3, null, response);
                }
                else if(interlaken_op1 != null && interlaken_op2 != null && interlaken_op3 == null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, interlaken_op2, null, interlaken_op4, response);
                }
                else if(interlaken_op1 != null && interlaken_op2 == null && interlaken_op3 != null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, null, interlaken_op3, interlaken_op4, response);
                }
                else if(interlaken_op1 == null && interlaken_op2 != null && interlaken_op3 != null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, null, interlaken_op2, interlaken_op3, interlaken_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(interlaken_op1 != null && interlaken_op2 != null && interlaken_op3 != null && interlaken_op4 != null) {
                    insertInterlakenItinerary(conn, log_username, interlaken_op1, interlaken_op2, interlaken_op3, interlaken_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Zurich insertions
    private void insertZurichItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Switzerland");
        ps.setString(4, "Zurich");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Switzerland.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Zurich - Inserted Rows: " + rows);
    }
    
    // Helper method for Lucerne insertions
    private void insertLucerneItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Switzerland");
        ps.setString(4, "Lucerne");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Switzerland.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Lucerne - Inserted Rows: " + rows);
    }
    
    // Helper method for Interlaken insertions
    private void insertInterlakenItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Europe");
        ps.setString(3, "Switzerland");
        ps.setString(4, "Interlaken");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Switzerland.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Interlaken - Inserted Rows: " + rows);
    }
}