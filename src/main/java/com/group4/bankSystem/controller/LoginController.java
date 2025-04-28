package com.group4.bankSystem.controller;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.dto.LoginRequest;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/api/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
      Object loggedInCustomer = session.getAttribute("loggedInCustomer");
      if (loggedInCustomer != null) {
          return ResponseEntity.ok("Session active");
      } else {
          return ResponseEntity.status(401).body("Not logged in");
      }
  }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Customer customer = customerRepository.findByCustomerTC(loginRequest.getCustomerTc());

        if (customer != null && customer.getLoginPasswordHash().equals(loginRequest.getPassword())) {
          session.setAttribute("customerId", customer.getCustomerId());
          return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("T.C. Kimlik veya Şifre yanlış!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Çıkış yapıldı.");
    }
}
