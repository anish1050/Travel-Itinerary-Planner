package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.JWTUtil;

/**
 * JWT Authentication Filter to validate tokens on protected routes
 */
@WebFilter("/*") // Enable this filter
public class JWTAuthenticationFilter implements Filter {

    // List of URLs that don't require authentication
    private static final List<String> PUBLIC_URLS = Arrays.asList(
        "/LoginPage.html",
        "/RegistrationPage.html", 
        "/HomePage.html",
        "/HomePage.jsp",
        "/User_Registration",
        "/User_Authentication",
        "/InitialUserLoginProfile",
        "/FinalUserLoginProfile",
        "/Debug_Authentication",
        "/DebugLogin.html",
        "/JWTLogout",
        "/UserLogout.jsp",
        "/ForgotPassword.html",
        "/ForgotPassword",
        "/error.html",
        "/RegistrationFailed.html",
        "/RegistrationSuccessfull.html",
        "/LoginFailed.html"
    );
    
    // List of file extensions that don't require authentication
    private static final List<String> PUBLIC_EXTENSIONS = Arrays.asList(
        ".css", ".js", ".jpg", ".jpeg", ".png", ".gif", ".ico", ".svg", ".mp4", ".webm", ".ogg"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        
        // Remove context path from request URI
        if (contextPath != null && !contextPath.isEmpty()) {
            requestURI = requestURI.substring(contextPath.length());
        }
        
        // Check if the request is for a public resource
        if (isPublicResource(requestURI)) {
            // Add no-cache headers for login page to prevent back button issues
            if (requestURI.equals("/LoginPage.html")) {
                setNoCacheHeaders(httpResponse);
            }
            chain.doFilter(request, response);
            return;
        }
        
        // Add no-cache headers for all protected pages
        setNoCacheHeaders(httpResponse);
        
        // Get JWT token from cookie
        String jwtToken = getJWTFromCookie(httpRequest);
        
        if (jwtToken != null && JWTUtil.validateToken(jwtToken)) {
            // Valid token - extract user info and set in session
            Map<String, String> userInfo = JWTUtil.getUserInfoFromToken(jwtToken);
            if (userInfo != null) {
                HttpSession session = httpRequest.getSession();
                session.setAttribute("s_log_u", userInfo.get("username"));
                session.setAttribute("s_user_email", userInfo.get("email"));
                session.setAttribute("s_user_firstname", userInfo.get("firstname"));
                session.setAttribute("s_user_lastname", userInfo.get("lastname"));
                session.setAttribute("jwt_token", jwtToken);
                
                // Set session timeout (30 minutes)
                session.setMaxInactiveInterval(30 * 60);
            }
            chain.doFilter(request, response);
        } else {
            // Invalid or missing token - clear any existing session and redirect to login
            HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            
            // Clear JWT cookie
            clearJWTCookie(httpResponse, contextPath);
            
            httpResponse.sendRedirect(contextPath + "/LoginPage.html");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
    
    /**
     * Set no-cache headers to prevent browser caching
     */
    private void setNoCacheHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }
    
    /**
     * Clear JWT cookie
     */
    private void clearJWTCookie(HttpServletResponse response, String contextPath) {
        Cookie jwtCookie = new Cookie("jwt_token", "");
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");  // <-- CHANGED TO ROOT PATH
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);
        System.out.println("JWT cookie cleared with path: /");
    }
    
    /**
     * Check if the requested resource is public (doesn't require authentication)
     */
    private boolean isPublicResource(String requestURI) {
        // Check exact URL matches
        for (String publicUrl : PUBLIC_URLS) {
            if (requestURI.equals(publicUrl) || requestURI.equals("/") || requestURI.isEmpty()) {
                return true;
            }
        }
        
        // Check file extensions
        for (String extension : PUBLIC_EXTENSIONS) {
            if (requestURI.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        
        // Check if it starts with public paths
        if (requestURI.startsWith("/css/") || 
            requestURI.startsWith("/js/") || 
            requestURI.startsWith("/images/") ||
            requestURI.startsWith("/assets/")) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Extract JWT token from HTTP cookies
     */
    private String getJWTFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        
        // Also check Authorization header as fallback
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        
        return null;
    }
}