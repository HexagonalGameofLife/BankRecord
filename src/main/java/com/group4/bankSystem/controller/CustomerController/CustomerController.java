package com.group4.bankSystem.controller.CustomerController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.bankSystem.dto.CustomerWithAccountsDTO;
import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository; // Hesap repository'sini ekle

    @PutMapping("/income")
    public ResponseEntity<?> updateIncome(@RequestBody Map<String, Object> body) {
        Integer customerId;
        Integer income;

        try {
            customerId = Integer.parseInt(body.get("customerId").toString());
            income = Integer.parseInt(body.get("income").toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid input.");
        }

        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }

        Customer customer = customerOpt.get();
        customer.setCustomerIncome(income);
        customerRepository.save(customer);

        return ResponseEntity.ok("Income updated.");
    }


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

    List<Account> accounts = accountRepository.findAllByCustomerId(customerId.longValue()); // ← güncellendi
    CustomerWithAccountsDTO dto = new CustomerWithAccountsDTO(customer, accounts);

    return ResponseEntity.ok(dto);
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
