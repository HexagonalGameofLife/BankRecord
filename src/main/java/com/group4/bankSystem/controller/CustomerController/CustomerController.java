package com.group4.bankSystem.controller.CustomerController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

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

        // 🔒 Güvenli Güncellenebilir Alanlar:
        if (updatedFields.containsKey("customerName")) {
            customer.setCustomerName(updatedFields.get("customerName"));
        }

        if (updatedFields.containsKey("customerSurname")) {
            customer.setCustomerSurname(updatedFields.get("customerSurname"));
        }

        if (updatedFields.containsKey("customerEmail")) {
            customer.setCustomerEmail(updatedFields.get("customerEmail"));
        }

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

        // ❌ TC, kayıt tarihi, gelir, limit gibi alanlar hiçbir şekilde değişmiyor

        customerRepository.save(customer);
        return ResponseEntity.ok("Profil başarıyla güncellendi.");
    }
}
