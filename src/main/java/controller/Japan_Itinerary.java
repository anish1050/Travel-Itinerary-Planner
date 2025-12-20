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

@WebServlet("/Japan_Itinerary")
public class Japan_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String tokyo_op1 = request.getParameter("tokyo_op1");
        String tokyo_op2 = request.getParameter("tokyo_op2");
        String tokyo_op3 = request.getParameter("tokyo_op3");
        
        String nikko_op1 = request.getParameter("nikko_op1");
        String nikko_op2 = request.getParameter("nikko_op2");
        String nikko_op3 = request.getParameter("nikko_op3");
        
        String kyoto_op1 = request.getParameter("kyoto_op1");
        String kyoto_op2 = request.getParameter("kyoto_op2");
        String kyoto_op3 = request.getParameter("kyoto_op3");
        String kyoto_op4 = request.getParameter("kyoto_op4");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // TOKYO SECTION - All combinations (2^3 - 1 = 7 combinations)
            if(cityname.equals("tokyo")) {
                
                // Single selections (3 combinations)
                if(tokyo_op1 != null && tokyo_op2 == null && tokyo_op3 == null) {
                    insertTokyoItinerary(conn, log_username, tokyo_op1, null, null, response);
                }
                else if(tokyo_op1 == null && tokyo_op2 != null && tokyo_op3 == null) {
                    insertTokyoItinerary(conn, log_username, null, tokyo_op2, null, response);
                }
                else if(tokyo_op1 == null && tokyo_op2 == null && tokyo_op3 != null) {
                    insertTokyoItinerary(conn, log_username, null, null, tokyo_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(tokyo_op1 != null && tokyo_op2 != null && tokyo_op3 == null) {
                    insertTokyoItinerary(conn, log_username, tokyo_op1, tokyo_op2, null, response);
                }
                else if(tokyo_op1 != null && tokyo_op2 == null && tokyo_op3 != null) {
                    insertTokyoItinerary(conn, log_username, tokyo_op1, null, tokyo_op3, response);
                }
                else if(tokyo_op1 == null && tokyo_op2 != null && tokyo_op3 != null) {
                    insertTokyoItinerary(conn, log_username, null, tokyo_op2, tokyo_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(tokyo_op1 != null && tokyo_op2 != null && tokyo_op3 != null) {
                    insertTokyoItinerary(conn, log_username, tokyo_op1, tokyo_op2, tokyo_op3, response);
                }
            }
            
            // NIKKO SECTION - All combinations (2^3 - 1 = 7 combinations)
            else if(cityname.equals("nikko")) {
                
                // Single selections (3 combinations)
                if(nikko_op1 != null && nikko_op2 == null && nikko_op3 == null) {
                    insertNikkoItinerary(conn, log_username, nikko_op1, null, null, response);
                }
                else if(nikko_op1 == null && nikko_op2 != null && nikko_op3 == null) {
                    insertNikkoItinerary(conn, log_username, null, nikko_op2, null, response);
                }
                else if(nikko_op1 == null && nikko_op2 == null && nikko_op3 != null) {
                    insertNikkoItinerary(conn, log_username, null, null, nikko_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(nikko_op1 != null && nikko_op2 != null && nikko_op3 == null) {
                    insertNikkoItinerary(conn, log_username, nikko_op1, nikko_op2, null, response);
                }
                else if(nikko_op1 != null && nikko_op2 == null && nikko_op3 != null) {
                    insertNikkoItinerary(conn, log_username, nikko_op1, null, nikko_op3, response);
                }
                else if(nikko_op1 == null && nikko_op2 != null && nikko_op3 != null) {
                    insertNikkoItinerary(conn, log_username, null, nikko_op2, nikko_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(nikko_op1 != null && nikko_op2 != null && nikko_op3 != null) {
                    insertNikkoItinerary(conn, log_username, nikko_op1, nikko_op2, nikko_op3, response);
                }
            }
            
            // KYOTO SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("kyoto")) {
                
                // Single selections (4 combinations)
                if(kyoto_op1 != null && kyoto_op2 == null && kyoto_op3 == null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, null, null, null, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 != null && kyoto_op3 == null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, null, kyoto_op2, null, null, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 == null && kyoto_op3 != null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, null, null, kyoto_op3, null, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 == null && kyoto_op3 == null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, null, null, null, kyoto_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(kyoto_op1 != null && kyoto_op2 != null && kyoto_op3 == null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, kyoto_op2, null, null, response);
                }
                else if(kyoto_op1 != null && kyoto_op2 == null && kyoto_op3 != null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, null, kyoto_op3, null, response);
                }
                else if(kyoto_op1 != null && kyoto_op2 == null && kyoto_op3 == null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, null, null, kyoto_op4, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 != null && kyoto_op3 != null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, null, kyoto_op2, kyoto_op3, null, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 != null && kyoto_op3 == null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, null, kyoto_op2, null, kyoto_op4, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 == null && kyoto_op3 != null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, null, null, kyoto_op3, kyoto_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(kyoto_op1 != null && kyoto_op2 != null && kyoto_op3 != null && kyoto_op4 == null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, kyoto_op2, kyoto_op3, null, response);
                }
                else if(kyoto_op1 != null && kyoto_op2 != null && kyoto_op3 == null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, kyoto_op2, null, kyoto_op4, response);
                }
                else if(kyoto_op1 != null && kyoto_op2 == null && kyoto_op3 != null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, null, kyoto_op3, kyoto_op4, response);
                }
                else if(kyoto_op1 == null && kyoto_op2 != null && kyoto_op3 != null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, null, kyoto_op2, kyoto_op3, kyoto_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(kyoto_op1 != null && kyoto_op2 != null && kyoto_op3 != null && kyoto_op4 != null) {
                    insertKyotoItinerary(conn, log_username, kyoto_op1, kyoto_op2, kyoto_op3, kyoto_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Tokyo insertions
    private void insertTokyoItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Japan");
        ps.setString(4, "Tokyo");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Japan.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Tokyo - Inserted Rows: " + rows);
    }
    
    // Helper method for Nikko insertions
    private void insertNikkoItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Japan");
        ps.setString(4, "Nikko");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Japan.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Nikko - Inserted Rows: " + rows);
    }
    
    // Helper method for Kyoto insertions
    private void insertKyotoItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Japan");
        ps.setString(4, "Kyoto");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Japan.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Kyoto - Inserted Rows: " + rows);
    }
}