package com.example.employeeApp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Secret key (must be 256-bit for HS256)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token expiration time: 1 day
    private final long jwtExpirationMs = 24 * 60 * 60 * 1000;

    // 🔑 Generate JWT token using username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extract username from token
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // ✅ Check if token is valid
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT unsupported");
        } catch (MalformedJwtException e) {
            System.out.println("JWT malformed");
        } catch (SignatureException e) {
            System.out.println("Invalid signature");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }
        return false;
    }

    // Helper method to parse token and verify signature
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
