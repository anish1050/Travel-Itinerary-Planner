package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String securityquestion = request.getParameter("security_question");
        String securityanswer = request.getParameter("security_answer");
        String newPassword = request.getParameter("new_password");
        
        String url = "jdbc:mysql://localhost:3306/java_project";
        String dbUsername = "root";
        String dbPassword = "Anish@1050";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT * FROM registered_users WHERE username = ? AND securityquestion = ? AND securityanswer = ?"
            );
            checkStmt.setString(1, username);
            checkStmt.setString(2, securityquestion);
            checkStmt.setString(3, securityanswer);
            
            ResultSet rs = checkStmt.executeQuery();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            if (rs.next()) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE registered_users SET password = ? WHERE username = ?"
                );
                updateStmt.setString(1, hashedPassword);
                updateStmt.setString(2, username);
                updateStmt.executeUpdate();
                
                out.println("<html><body>");
                out.println("<script>");
                out.println("alert('Password updated successfully!');");
                out.println("window.location.href = 'LoginPage.html';");
                out.println("</script>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<script>");
                out.println("alert('Invalid username or security answer!');");
                out.println("window.location.href = 'ForgotPassword.html';");
                out.println("</script>");
                out.println("</body></html>");
            }
            
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
