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

@WebServlet("/Indonesia_Itinerary")
public class Indonesia_Itinerary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        String log_username = (String) session.getAttribute("s_log_u");
        response.setContentType("text/html");
        
        String cityname = request.getParameter("city");
        
        String jakarta_op1 = request.getParameter("jakarta_op1");
        String jakarta_op2 = request.getParameter("jakarta_op2");
        String jakarta_op3 = request.getParameter("jakarta_op3");
        String jakarta_op4 = request.getParameter("jakarta_op4");
        
        String yogya_op1 = request.getParameter("yogya_op1");
        String yogya_op2 = request.getParameter("yogya_op2");
        String yogya_op3 = request.getParameter("yogya_op3");
        String yogya_op4 = request.getParameter("yogya_op4");
        
        String bali_op1 = request.getParameter("bali_op1");
        String bali_op2 = request.getParameter("bali_op2");
        String bali_op3 = request.getParameter("bali_op3");
        String bali_op4 = request.getParameter("bali_op4");
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            // JAKARTA SECTION - All combinations (2^4 - 1 = 15 combinations)
            if(cityname.equals("jakarta")) {
                
                // Single selections (4 combinations)
                if(jakarta_op1 != null && jakarta_op2 == null && jakarta_op3 == null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, null, null, null, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 != null && jakarta_op3 == null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, null, jakarta_op2, null, null, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 == null && jakarta_op3 != null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, null, null, jakarta_op3, null, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 == null && jakarta_op3 == null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, null, null, null, jakarta_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(jakarta_op1 != null && jakarta_op2 != null && jakarta_op3 == null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, jakarta_op2, null, null, response);
                }
                else if(jakarta_op1 != null && jakarta_op2 == null && jakarta_op3 != null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, null, jakarta_op3, null, response);
                }
                else if(jakarta_op1 != null && jakarta_op2 == null && jakarta_op3 == null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, null, null, jakarta_op4, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 != null && jakarta_op3 != null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, null, jakarta_op2, jakarta_op3, null, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 != null && jakarta_op3 == null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, null, jakarta_op2, null, jakarta_op4, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 == null && jakarta_op3 != null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, null, null, jakarta_op3, jakarta_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(jakarta_op1 != null && jakarta_op2 != null && jakarta_op3 != null && jakarta_op4 == null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, jakarta_op2, jakarta_op3, null, response);
                }
                else if(jakarta_op1 != null && jakarta_op2 != null && jakarta_op3 == null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, jakarta_op2, null, jakarta_op4, response);
                }
                else if(jakarta_op1 != null && jakarta_op2 == null && jakarta_op3 != null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, null, jakarta_op3, jakarta_op4, response);
                }
                else if(jakarta_op1 == null && jakarta_op2 != null && jakarta_op3 != null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, null, jakarta_op2, jakarta_op3, jakarta_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(jakarta_op1 != null && jakarta_op2 != null && jakarta_op3 != null && jakarta_op4 != null) {
                    insertJakartaItinerary(conn, log_username, jakarta_op1, jakarta_op2, jakarta_op3, jakarta_op4, response);
                }
            }
            
            // YOGYAKARTA SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("yogyakarta")) {
                
                // Single selections (4 combinations)
                if(yogya_op1 != null && yogya_op2 == null && yogya_op3 == null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, null, null, null, response);
                }
                else if(yogya_op1 == null && yogya_op2 != null && yogya_op3 == null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, null, yogya_op2, null, null, response);
                }
                else if(yogya_op1 == null && yogya_op2 == null && yogya_op3 != null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, null, null, yogya_op3, null, response);
                }
                else if(yogya_op1 == null && yogya_op2 == null && yogya_op3 == null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, null, null, null, yogya_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(yogya_op1 != null && yogya_op2 != null && yogya_op3 == null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, yogya_op2, null, null, response);
                }
                else if(yogya_op1 != null && yogya_op2 == null && yogya_op3 != null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, null, yogya_op3, null, response);
                }
                else if(yogya_op1 != null && yogya_op2 == null && yogya_op3 == null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, null, null, yogya_op4, response);
                }
                else if(yogya_op1 == null && yogya_op2 != null && yogya_op3 != null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, null, yogya_op2, yogya_op3, null, response);
                }
                else if(yogya_op1 == null && yogya_op2 != null && yogya_op3 == null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, null, yogya_op2, null, yogya_op4, response);
                }
                else if(yogya_op1 == null && yogya_op2 == null && yogya_op3 != null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, null, null, yogya_op3, yogya_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(yogya_op1 != null && yogya_op2 != null && yogya_op3 != null && yogya_op4 == null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, yogya_op2, yogya_op3, null, response);
                }
                else if(yogya_op1 != null && yogya_op2 != null && yogya_op3 == null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, yogya_op2, null, yogya_op4, response);
                }
                else if(yogya_op1 != null && yogya_op2 == null && yogya_op3 != null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, null, yogya_op3, yogya_op4, response);
                }
                else if(yogya_op1 == null && yogya_op2 != null && yogya_op3 != null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, null, yogya_op2, yogya_op3, yogya_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(yogya_op1 != null && yogya_op2 != null && yogya_op3 != null && yogya_op4 != null) {
                    insertYogyakartaItinerary(conn, log_username, yogya_op1, yogya_op2, yogya_op3, yogya_op4, response);
                }
            }
            
            // BALI SECTION - All combinations (2^4 - 1 = 15 combinations)
            else if(cityname.equals("bali")) {
                
                // Single selections (4 combinations)
                if(bali_op1 != null && bali_op2 == null && bali_op3 == null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, bali_op1, null, null, null, response);
                }
                else if(bali_op1 == null && bali_op2 != null && bali_op3 == null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, null, bali_op2, null, null, response);
                }
                else if(bali_op1 == null && bali_op2 == null && bali_op3 != null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, null, null, bali_op3, null, response);
                }
                else if(bali_op1 == null && bali_op2 == null && bali_op3 == null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, null, null, null, bali_op4, response);
                }
                
                // Double selections (6 combinations)
                else if(bali_op1 != null && bali_op2 != null && bali_op3 == null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, bali_op1, bali_op2, null, null, response);
                }
                else if(bali_op1 != null && bali_op2 == null && bali_op3 != null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, bali_op1, null, bali_op3, null, response);
                }
                else if(bali_op1 != null && bali_op2 == null && bali_op3 == null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, bali_op1, null, null, bali_op4, response);
                }
                else if(bali_op1 == null && bali_op2 != null && bali_op3 != null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, null, bali_op2, bali_op3, null, response);
                }
                else if(bali_op1 == null && bali_op2 != null && bali_op3 == null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, null, bali_op2, null, bali_op4, response);
                }
                else if(bali_op1 == null && bali_op2 == null && bali_op3 != null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, null, null, bali_op3, bali_op4, response);
                }
                
                // Triple selections (4 combinations)
                else if(bali_op1 != null && bali_op2 != null && bali_op3 != null && bali_op4 == null) {
                    insertBaliItinerary(conn, log_username, bali_op1, bali_op2, bali_op3, null, response);
                }
                else if(bali_op1 != null && bali_op2 != null && bali_op3 == null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, bali_op1, bali_op2, null, bali_op4, response);
                }
                else if(bali_op1 != null && bali_op2 == null && bali_op3 != null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, bali_op1, null, bali_op3, bali_op4, response);
                }
                else if(bali_op1 == null && bali_op2 != null && bali_op3 != null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, null, bali_op2, bali_op3, bali_op4, response);
                }
                
                // Quadruple selection (1 combination)
                else if(bali_op1 != null && bali_op2 != null && bali_op3 != null && bali_op4 != null) {
                    insertBaliItinerary(conn, log_username, bali_op1, bali_op2, bali_op3, bali_op4, response);
                }
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for Jakarta insertions
    private void insertJakartaItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Indonesia");
        ps.setString(4, "Jakarta");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Indonesia.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Jakarta - Inserted Rows: " + rows);
    }
    
    // Helper method for Yogyakarta insertions
    private void insertYogyakartaItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Indonesia");
        ps.setString(4, "Yogyakarta");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Indonesia.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Yogyakarta - Inserted Rows: " + rows);
    }
    
    // Helper method for Bali insertions
    private void insertBaliItinerary(Connection conn, String username, String op1, String op2, String op3, String op4, HttpServletResponse response) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users_itinerary (username, continent, country, city, activity1, activity2, activity3, activity4) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        ps.setString(1, username);
        ps.setString(2, "Asia");
        ps.setString(3, "Indonesia");
        ps.setString(4, "Bali");
        ps.setString(5, op1);
        ps.setString(6, op2);
        ps.setString(7, op3);
        ps.setString(8, op4);
        
        int rows = ps.executeUpdate();
        
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Itinerary Updated');");
        out.println("setTimeout(function() { window.location.href = 'Indonesia.html'; }, 1000);");
        out.println("</script>");
        out.println("</body></html>");
        
        System.out.println("Bali - Inserted Rows: " + rows);
    }
}