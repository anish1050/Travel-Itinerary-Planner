package controller;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.*;

@WebServlet("/User_Authentication")
public class User_Authentication extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect(request.getContextPath() + "/LoginPage.html");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String log_u = request.getParameter("log_username");
        String log_p = request.getParameter("log_password");
        
        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Username: " + log_u);
        System.out.println("Context Path: " + request.getContextPath());
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBConnection.getConnection();
            System.out.println("Connection Established");
            
            String sql = "SELECT firstname, lastname, email, password FROM registered_users WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, log_u);
            resultSet = statement.executeQuery();
            
            boolean loginSuccessful = false;
            String firstname = "";
            String lastname = "";
            String email = "";
            
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                firstname = resultSet.getString("firstname");
                lastname = resultSet.getString("lastname");
                email = resultSet.getString("email");
                
                System.out.println("User found in database");
                System.out.println("Firstname: " + firstname);
                System.out.println("Lastname: " + lastname);
                System.out.println("Email: " + email);
                
                if (BCrypt.checkpw(log_p, storedPassword)) {
                    loginSuccessful = true;
                    System.out.println("Password match - login successful");
                } else {
                    System.out.println("Password mismatch");
                }
            } else {
                System.out.println("User not found in database");
            }
            
            if (loginSuccessful) {
                System.out.println("=== GENERATING JWT TOKEN ===");
                
                // Check if response is already committed
                System.out.println("Response committed: " + response.isCommitted());
                
                // Generate JWT token FIRST
                String token = JWTUtil.generateToken(log_u, email, firstname, lastname);
                
                System.out.println("Token generated: " + (token != null ? "YES" : "NO"));
                if (token != null && !response.isCommitted()) {
                    System.out.println("Token length: " + token.length());
                    
                    // Set cookie path to root "/" - DO NOT USE request.getContextPath()
                    Cookie jwtCookie = new Cookie("jwt_token", token);
                    jwtCookie.setMaxAge(24 * 60 * 60);
                    jwtCookie.setPath("/");  // <-- THIS MUST BE "/" NOT contextPath
                    jwtCookie.setHttpOnly(true);
                    response.addCookie(jwtCookie);
                    
                    System.out.println("Cookie added with path: /");
                    
                    // Set session attributes
                    HttpSession session = request.getSession(true);
                    session.setAttribute("s_log_u", log_u);
                    session.setAttribute("s_user_email", email);
                    session.setAttribute("s_user_firstname", firstname);
                    session.setAttribute("s_user_lastname", lastname);
                    session.setAttribute("jwt_token", token);
                    session.setMaxInactiveInterval(30 * 60);
                    
                    System.out.println("Session attributes set");
                    System.out.println("Redirecting to InitialUserLoginProfile");
                    
                    // Redirect
                    response.sendRedirect(request.getContextPath() + "/InitialUserLoginProfile");
                } else {
                    System.out.println("ERROR: Cannot set cookie - response committed or token is null");
                    if (response.isCommitted()) {
                        System.out.println("Response was already committed!");
                    }
                    response.sendRedirect("error.html");
                }
            }
            else {
                System.out.println("Login failed - redirecting to LoginFailed.html");
                response.sendRedirect("LoginFailed.html?error=invalid_credentials");
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Database driver not found");
            e.printStackTrace();
            response.sendRedirect("error.html");
        } catch (SQLException e) {
            System.out.println("ERROR: SQL exception");
            e.printStackTrace();
            response.sendRedirect("error.html");
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected exception");
            e.printStackTrace();
            response.sendRedirect("error.html");
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
                System.out.println("Database resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("=== LOGIN PROCESS COMPLETED ===\n");
    }
}