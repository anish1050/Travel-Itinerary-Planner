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

@WebServlet("/China_Itinerary")
public class China_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String beijing_op1 = request.getParameter("beijing_op1");
        String beijing_op2 = request.getParameter("beijing_op2");
        String beijing_op3 = request.getParameter("beijing_op3");
        
        String xi_op1 = request.getParameter("xi_op1");
        String xi_op2 = request.getParameter("xi_op2");
        String xi_op3 = request.getParameter("xi_op3");
        
        String chengdu_op1 = request.getParameter("chengdu_op1");
        String chengdu_op2 = request.getParameter("chengdu_op2");
        String chengdu_op3 = request.getParameter("chengdu_op3");
        String chengdu_op4 = request.getParameter("chengdu_op4");
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // BEIJING SECTION - All combinations (2^3 - 1 = 7 combinations)
            if(cityname.equals("beijing")) {
                
                // Single selections (3 combinations)
                if(beijing_op1 != null && beijing_op2 == null && beijing_op3 == null) {
                    insertBeijingItinerary(conn, log_username, beijing_op1, null, null, response);
                }
                else if(beijing_op1 == null && beijing_op2 != null && beijing_op3 == null) {
                    insertBeijingItinerary(conn, log_username, null, beijing_op2, null, response);
                }
                else if(beijing_op1 == null && beijing_op2 == null && beijing_op3 != null) {
                    insertBeijingItinerary(conn, log_username, null, null, beijing_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(beijing_op1 != null && beijing_op2 != null && beijing_op3 == null) {
                    insertBeijingItinerary(conn, log_username, beijing_op1, beijing_op2, null, response);
                }
                else if(beijing_op1 != null && beijing_op2 == null && beijing_op3 != null) {
                    insertBeijingItinerary(conn, log_username, beijing_op1, null, beijing_op3, response);
                }
                else if(beijing_op1 == null && beijing_op2 != null && beijing_op3 != null) {
                    insertBeijingItinerary(conn, log_username, null, beijing_op2, beijing_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(beijing_op1 != null && beijing_op2 != null && beijing_op3 != null) {
                    insertBeijingItinerary(conn, log_username, beijing_op1, beijing_op2, beijing_op3, response);
                }
            }
            
            // XI'AN SECTION - All combinations (2^3 - 1 = 7 combinations)
            else if(cityname.equals("xi")) {
                
                // Single selections (3 combinations)
                if(xi_op1 != null && xi_op2 == null && xi_op3 == null) {
                    insertXiItinerary(conn, log_username, xi_op1, null, null, response);
                }
                else if(xi_op1 == null && xi_op2 != null && xi_op3 == null) {
                    insertXiItinerary(conn, log_username, null, xi_op2, null, response);
                }
                else if(xi_op1 == null && xi_op2 == null && xi_op3 != null) {
                    insertXiItinerary(conn, log_username, null, null, xi_op3, response);
                }
                
                // Double selections (3 combinations)
                else if(xi_op1 != null && xi_op2 != null && xi_op3 == null) {
                    insertXiItinerary(conn, log_username, xi_op1, xi_op2, null, response);
                }
                else if(xi_op1 != null && xi_op2 == null && xi_op3 != null) {
                    insertXiItinerary(conn, log_username, xi_op1, null, xi_op3, response);
                }
                else if(xi_op1 == null && xi_op2 != null && xi_op3 != null) {
                    insertXiItinerary(conn, log_username, null, xi_op2, xi_op3, response);
                }
                
                // Triple selection (1 combination)
                else if(xi_op1 != null && xi_op2 != null && xi_op3 != null) {
                    insertXiItinerary(conn, log_username, xi_op1, xi_op2, xi_op3, response);
                }
            }
            
            // CHENGDU SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("chengdu")) {
                
                // Single selections (4 combinations)
                if(chengdu_op1 != null && chengdu_op2 == null && chengdu_op3 == null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, null, null, null, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 != null && chengdu_op3 == null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, null, chengdu_op2, null, null, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 == null && chengdu_op3 != null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, null, null, chengdu_op3, null, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 == null && chengdu_op3 == null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, null, null, null, chengdu_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(chengdu_op1 != null && chengdu_op2 != null && chengdu_op3 == null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, chengdu_op2, null, null, response);
                }
                else if(chengdu_op1 != null && chengdu_op2 == null && chengdu_op3 != null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, null, chengdu_op3, null, response);
                }
                else if(chengdu_op1 != null && chengdu_op2 == null && chengdu_op3 == null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, null, null, chengdu_op4, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 != null && chengdu_op3 != null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, null, chengdu_op2, chengdu_op3, null, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 != null && chengdu_op3 == null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, null, chengdu_op2, null, chengdu_op4, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 == null && chengdu_op3 != null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, null, null, chengdu_op3, chengdu_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(chengdu_op1 != null && chengdu_op2 != null && chengdu_op3 != null && chengdu_op4 == null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, chengdu_op2, chengdu_op3, null, response);
                }
                else if(chengdu_op1 != null && chengdu_op2 != null && chengdu_op3 == null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, chengdu_op2, null, chengdu_op4, response);
                }
                else if(chengdu_op1 != null && chengdu_op2 == null && chengdu_op3 != null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, null, chengdu_op3, chengdu_op4, response);
                }
                else if(chengdu_op1 == null && chengdu_op2 != null && chengdu_op3 != null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, null, chengdu_op2, chengdu_op3, chengdu_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(chengdu_op1 != null && chengdu_op2 != null && chengdu_op3 != null && chengdu_op4 != null) {
                    insertChengduItinerary(conn, log_username, chengdu_op1, chengdu_op2, chengdu_op3, chengdu_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Beijing insertions
    private void insertBeijingItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "China");
        ps.setString(4, "Beijing");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'China.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Beijing - Inserted Rows: " + rows);
    }
    
    // Helper method for Xi'an insertions
    private void insertXiItinerary(Connection conn, String username, String op1, String op2, String op3, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "China");
        ps.setString(4, "Xi'an");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'China.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Xi'an - Inserted Rows: " + rows);
    }
    
    // Helper method for Chengdu insertions
    private void insertChengduItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "China");
        ps.setString(4, "Chengdu");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'China.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Chengdu - Inserted Rows: " + rows);
    }
}