package com.group4.bankSystem.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.dto.RegisterRequest;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest) {



        // TC Kimlik Numarası kontrolü (11 haneli ve geçerli)
        if (!isValidTurkishID(registerRequest.getCustomerTc())) {
         return ResponseEntity.badRequest().body("Geçerli bir TC Kimlik Numarası giriniz.");
        }

        if (customerRepository.findByCustomerTC(registerRequest.getCustomerTc()) != null) {
            return ResponseEntity.badRequest().body("Bu TC numarası ile zaten bir kayıt var.");
        }

        // Telefon kontrolü
        if (customerRepository.findByCustomerPhoneNumber(registerRequest.getCustomerPhoneNumber()) != null) {
            return ResponseEntity.badRequest().body("Bu telefon numarası ile zaten bir kayıt var.");
        }

        // Telefon numarası kontrolü (başında 05 ile başlayan, 11 haneli)
        if (!registerRequest.getCustomerPhoneNumber().matches("^0\\d{10}$")) {
            return ResponseEntity.badRequest().body("Telefon numarası geçersiz. 0(XXX)(XXX)(XX)(XX) formatında olmalıdır.");
        }

        // E-posta kontrolü
        if (customerRepository.findByCustomerEmail(registerRequest.getCustomerEmail()) != null) {
            return ResponseEntity.badRequest().body("Bu e-posta adresi ile zaten bir kayıt var.");
        }

        // E-posta kontrolü
        if (!registerRequest.getCustomerEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return ResponseEntity.badRequest().body("Geçerli bir e-posta adresi giriniz.");
        }

        // Şifre kontrolü (en az 6 karakter, harf ve sayı içermeli)
        if (!registerRequest.getPassword().matches("^\\d{6}$")) {
            return ResponseEntity.badRequest().body("Şifre yalnızca 6 haneli rakamlardan oluşmalıdır.");
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

    private boolean isValidTurkishID(String tc) {
        if (tc == null || !tc.matches("^\\d{11}$")) {
            return false;
        }

        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += Character.getNumericValue(tc.charAt(i));
        }

        int lastDigit = Character.getNumericValue(tc.charAt(10));

        return total % 10 == lastDigit;
    }

}
