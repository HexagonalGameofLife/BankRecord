package com.group4.bankSystem.controller;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.dto.RegisterRequest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest) {

        if (customerRepository.findByCustomerTC(registerRequest.getCustomerTc()) != null) {
            return ResponseEntity.badRequest().body("Bu TC numarası ile zaten bir kayıt var.");
        }

        Customer customer = new Customer();
        customer.setCustomerTC(registerRequest.getCustomerTc());
        customer.setCustomerName(registerRequest.getCustomerName());
        customer.setCustomerSurname(registerRequest.getCustomerSurname());
        customer.setCustomerBirthdate(registerRequest.getCustomerBirthdate());
        customer.setCustomerPhoneNumber(registerRequest.getCustomerPhoneNumber());
        customer.setCustomerEmail(registerRequest.getCustomerEmail());
        customer.setLoginPasswordHash(registerRequest.getPassword());
        customer.setCustomerRegistrationDate(LocalDate.now());


        customerRepository.save(customer);

        return ResponseEntity.ok("Kayıt başarılı!");
    }
}
