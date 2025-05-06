package com.group4.bankSystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/api/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("customerId", customerId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }

}
