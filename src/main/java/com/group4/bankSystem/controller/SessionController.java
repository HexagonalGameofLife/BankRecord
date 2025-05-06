package com.group4.bankSystem.controller;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

=======
import jakarta.servlet.http.HttpSession;
>>>>>>> smhavci/Semih-Son
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import jakarta.servlet.http.HttpSession;

=======
>>>>>>> smhavci/Semih-Son
@RestController
public class SessionController {

    @GetMapping("/api/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
<<<<<<< HEAD
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("customerId", customerId);
            return ResponseEntity.ok(response);
=======
        if (session.getAttribute("customerId") != null) {
            return ResponseEntity.ok("Session active");
>>>>>>> smhavci/Semih-Son
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }
<<<<<<< HEAD

=======
>>>>>>> smhavci/Semih-Son
}
