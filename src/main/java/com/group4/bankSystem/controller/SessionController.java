package com.group4.bankSystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/api/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        if (session.getAttribute("customerId") != null) {
            return ResponseEntity.ok("Session active");
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }
}
