package com.group4.bankSystem.controller.CustomerController;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentCustomer(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return ResponseEntity.status(401).body("Giriş yapılmamış.");
        }

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.status(404).body("Müşteri bulunamadı.");
        }

        return ResponseEntity.ok(customer);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentCustomer(@RequestBody Map<String, String> updatedFields, HttpSession session) {
      Integer customerId = (Integer) session.getAttribute("customerId");
      if (customerId == null) {
          return ResponseEntity.status(401).body("Login olunmamış.");
      }

      Customer customer = customerRepository.findById(customerId).orElse(null);
      if (customer == null) {
          return ResponseEntity.status(404).body("Müşteri bulunamadı.");
      }

      // JSON map olduğu için alanları böyle okuyacağız:
      if (updatedFields.containsKey("customerPhoneNumber")) {
          customer.setCustomerPhoneNumber(updatedFields.get("customerPhoneNumber"));
      }
      if (updatedFields.containsKey("customerGender")) {
          customer.setCustomerGender(updatedFields.get("customerGender"));
      }
      if (updatedFields.containsKey("customerAddress")) {
          customer.setCustomerAddress(updatedFields.get("customerAddress"));
      }
      if (updatedFields.containsKey("loginPasswordHash")) {
          String newPassword = updatedFields.get("loginPasswordHash");
          if (newPassword != null && !newPassword.isEmpty()) {
              customer.setLoginPasswordHash(newPassword);
          }
      }

      customerRepository.save(customer);

      return ResponseEntity.ok("Profil güncellendi.");
  }

}
