package controller;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import util.DBConnection;
import util.JWTUtil;

@WebServlet("/User_Registration")
public class User_Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String reg_f = request.getParameter("reg_fname");
        String reg_l = request.getParameter("reg_lname");
        String reg_u = request.getParameter("reg_username");
        String reg_pass = request.getParameter("reg_password");
        String reg_e = request.getParameter("reg_email");
        String reg_pnum = request.getParameter("reg_pnumber");
        String securityq = request.getParameter("security_question");
        String securitya = request.getParameter("security_answer");
        
        HttpSession session = request.getSession();
        session.setAttribute("s_reg_fanme", reg_f);
        session.setAttribute("s_reg_lname", reg_l);
        session.setAttribute("s_reg_u", reg_u);
        session.setAttribute("s_reg_pass", reg_pass);
        session.setAttribute("s_reg_e", reg_e);
        session.setAttribute("s_reg_pnum", reg_pnum);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            
            PreparedStatement checkStmt = conn.prepareStatement("SELECT username FROM registered_users WHERE username = ?");
            checkStmt.setString(1, reg_u);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                
       			response.setContentType("text/html");
       			PrintWriter out = response.getWriter();
       			
            	out.println("<html><body>");
       			
       			out.println("<script>");
       			
       			out.println("alert('Username already exists');"); 
       			
       			out.println("window.location.href = 'RegistrationPage.html';"); 
       			
       			out.println("</script>");
       			
       			out.println("</body></html>");
       			
                session.setAttribute("registrationError", "Username already exists. Please choose a different username.");
                
            } 
            
            else if(!(reg_e.contains("@") && reg_e.contains(".com"))) {
            	
            	response.setContentType("text/html");
       			PrintWriter out = response.getWriter();
       			
            	out.println("<html><body>");
       			
       			out.println("<script>");
       			
       			out.println("alert('Invalid Email');"); 
       			
       			out.println("window.location.href = 'RegistrationPage.html';"); 
       			
       			out.println("</script>");
       			
       			out.println("</body></html>");
       			
                session.setAttribute("registrationError", "Invalid Email.");
                
            }
            
            else if(reg_pnum.length() !=10) {
            	
            	response.setContentType("text/html");
       			PrintWriter out = response.getWriter();
       			
            	out.println("<html><body>");
       			
       			out.println("<script>");
       			
       			out.println("alert('Invalid Phone number');"); 
       			
       			out.println("window.location.href = 'RegistrationPage.html';"); 
       			
       			out.println("</script>");
       			
       			out.println("</body></html>");
       			
                session.setAttribute("registrationError", "Invalid Email.");
                
            }
            
            else {
            	
            	 PreparedStatement countStmt = conn.prepareStatement(
            		        "SELECT COUNT(*) FROM registered_users"
            		    );
            		    ResultSet countRs = countStmt.executeQuery();
            		    countRs.next();
            		    int userCount = countRs.getInt(1);

            		    if (userCount >= 5) {
            		        PreparedStatement deleteStmt = conn.prepareStatement(
            		            "DELETE FROM registered_users " +
            		            "WHERE username <> 'anish' " +
            		            "ORDER BY created_at ASC " +
            		            "LIMIT 1"
            		        );
            		        deleteStmt.executeUpdate();
            		    }

                PreparedStatement ps = conn.prepareStatement("INSERT INTO registered_users (firstname, lastname, username, password, email, phonenumber, securityquestion, securityanswer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                
                ps.setString(1, reg_f);
                ps.setString(2, reg_l);
                ps.setString(3, reg_u);
               
                String hashedPassword = BCrypt.hashpw(reg_pass, BCrypt.gensalt(12));
                ps.setString(4, hashedPassword);
                
                ps.setString(5, reg_e);
                ps.setString(6, reg_pnum);
                ps.setString(7, securityq);
                ps.setString(8, securitya);
                
                int rows = ps.executeUpdate();
                System.out.println("Inserted Rows: " + rows);
                
                if (rows > 0) {
                    // Registration successful, generate JWT token
                    String jwtToken = JWTUtil.generateToken(reg_u, reg_e, reg_f, reg_l);
                    
                    if (jwtToken != null) {
                        // Set JWT token as HTTP-only cookie
                        Cookie jwtCookie = new Cookie("jwt_token", jwtToken);
                        jwtCookie.setMaxAge(24 * 60 * 60); // 24 hours
                        jwtCookie.setPath("/");
                        jwtCookie.setHttpOnly(true); // Prevent XSS attacks
                        // jwtCookie.setSecure(true); // Enable in production with HTTPS
                        response.addCookie(jwtCookie);
                        
                        // Set user info in session
                        session.setAttribute("s_log_u", reg_u);
                        session.setAttribute("s_user_email", reg_e);
                        session.setAttribute("s_user_firstname", reg_f);
                        session.setAttribute("s_user_lastname", reg_l);
                        session.setAttribute("jwt_token", jwtToken);
                    }
                }
                
                response.sendRedirect("RegistrationSuccessfull.html");
            }
            
            conn.close();
            
        } catch(Exception e) {
            
            
            e.printStackTrace();
        }
    }
}