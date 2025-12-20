package util;

import java.util.Date;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    
    private static final String SECRET_KEY = "WanderCraft_Secret_Key_2024_Travel_Itinerary_Planner_JWT_Security";
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours
    private static final String ALGORITHM = "HmacSHA256";
    
    /**
     * Generate JWT token for authenticated user
     */
    public static String generateToken(String username, String email, String firstname, String lastname) {
        try {
            System.out.println("=== JWT TOKEN GENERATION ===");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Firstname: " + firstname);
            System.out.println("Lastname: " + lastname);

            // Create header
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            // Create payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("username", username);
            payload.put("email", email);
            payload.put("firstname", firstname);
            payload.put("lastname", lastname);
            payload.put("iat", System.currentTimeMillis() / 1000); // Issued at
            payload.put("exp", (System.currentTimeMillis() + EXPIRATION_TIME) / 1000); // Expiration

            System.out.println("Payload created");

            // Convert to JSON and encode
            String headerJson = mapToJson(header);
            String payloadJson = mapToJson(payload);

            System.out.println("Header JSON: " + headerJson);
            System.out.println("Payload JSON: " + payloadJson);

            String encodedHeader = base64UrlEncode(headerJson);
            String encodedPayload = base64UrlEncode(payloadJson);

            System.out.println("Header encoded: " + encodedHeader);
            System.out.println("Payload encoded: " + encodedPayload);

            // Create signature
            String dataToSign = encodedHeader + "." + encodedPayload;
            String signature = createSignature(dataToSign);

            System.out.println("Signature created: " + signature);

            // Return complete token
            String token = encodedHeader + "." + encodedPayload + "." + signature;
            System.out.println("Complete token generated successfully");
            System.out.println("Token length: " + token.length());

            return token;

        } catch (Exception e) {
            System.out.println("ERROR in generateToken:");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Validate JWT token
     */
    public static boolean validateToken(String token) {
        try {
            if (token == null || token.isEmpty()) {
                return false;
            }
            
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }
            
            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];
            
            // Verify signature
            String dataToVerify = header + "." + payload;
            String expectedSignature = createSignature(dataToVerify);
            
            if (!signature.equals(expectedSignature)) {
                return false;
            }
            
            // Check expiration
            String payloadJson = base64UrlDecode(payload);
            Map<String, String> payloadMap = jsonToMap(payloadJson);
            
            long exp = Long.parseLong(payloadMap.get("exp"));
            long currentTime = System.currentTimeMillis() / 1000;
            
            return exp > currentTime;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Extract username from JWT token
     */
    public static String getUsernameFromToken(String token) {
        try {
            if (!validateToken(token)) {
                return null;
            }
            
            String[] parts = token.split("\\.");
            String payloadJson = base64UrlDecode(parts[1]);
            Map<String, String> payloadMap = jsonToMap(payloadJson);
            
            return payloadMap.get("username");
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Extract user information from JWT token
     */
    public static Map<String, String> getUserInfoFromToken(String token) {
        try {
            if (!validateToken(token)) {
                return null;
            }
            
            String[] parts = token.split("\\.");
            String payloadJson = base64UrlDecode(parts[1]);
            Map<String, String> payloadMap = jsonToMap(payloadJson);
            
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("username", payloadMap.get("username"));
            userInfo.put("email", payloadMap.get("email"));
            userInfo.put("firstname", payloadMap.get("firstname"));
            userInfo.put("lastname", payloadMap.get("lastname"));
            
            return userInfo;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Check if token is expired
     */
    public static boolean isTokenExpired(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return true;
            }
            
            String payloadJson = base64UrlDecode(parts[1]);
            Map<String, String> payloadMap = jsonToMap(payloadJson);
            
            long exp = Long.parseLong(payloadMap.get("exp"));
            long currentTime = System.currentTimeMillis() / 1000;
            
            return exp <= currentTime;
            
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    
    /**
     * Create HMAC signature
     */
    private static String createSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        mac.init(secretKeySpec);
        byte[] signature = mac.doFinal(data.getBytes());
        return base64UrlEncode(signature);
    }
    
    /**
     * Base64 URL encode
     */
    private static String base64UrlEncode(String data) {
        return base64UrlEncode(data.getBytes());
    }
    
    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
    
    /**
     * Base64 URL decode
     */
    private static String base64UrlDecode(String encodedData) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedData);
        return new String(decodedBytes);
    }
    
    /**
     * Simple JSON serialization for Map to JSON string (for Object values)
     * THIS METHOD IS NEEDED FOR TOKEN GENERATION
     */
    private static String mapToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                json.append("\"").append(entry.getValue()).append("\"");
            } else {
                json.append(entry.getValue());
            }
            first = false;
        }
        json.append("}");
        return json.toString();
    }
    
    /**
     * Simple JSON parsing for JSON string to Map
     * THIS METHOD IS NEEDED FOR TOKEN VALIDATION
     */
    private static Map<String, String> jsonToMap(String json) {
        Map<String, String> map = new HashMap<>();
        // Remove braces
        json = json.trim();
        if (json.startsWith("{")) {
            json = json.substring(1);
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 1);
        }
        
        // Split by comma and parse key-value pairs
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "");
                map.put(key, value);
            }
        }
        
        return map;
    }
}