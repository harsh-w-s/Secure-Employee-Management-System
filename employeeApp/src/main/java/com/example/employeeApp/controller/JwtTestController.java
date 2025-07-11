package com.example.employeeApp.controller;

import com.example.employeeApp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class JwtTestController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/token")
    public String generateToken(@RequestParam String username) {
        return jwtUtils.generateToken(username);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        boolean isValid = jwtUtils.validateToken(token);
        return isValid ? "Token is valid ✅" : "Invalid or expired token ❌";
    }

    @GetMapping("/username")
    public String getUsername(@RequestParam String token) {
        return jwtUtils.extractUsername(token);
    }
}