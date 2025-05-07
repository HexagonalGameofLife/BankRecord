package com.group4.bankSystem.services;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.AccountEntities.Card;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.AccountRepository.CardRepository;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;


@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository,
            CustomerRepository customerRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Şifreleme için
    }

    public Card createCardForAccount(Long accountId, String pin) {
        Account account = accountRepository.findById(accountId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Customer customer = customerRepository.findById(account.getPrimaryUserId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("Invalid PIN: Must be 4 digits");
        }

        Card card = new Card();
        card.setCardNumber(generateRandomCardNumber());
        card.setCvv(generateRandomCVV());
        card.setExpireDate(Date.valueOf(LocalDate.now().plusYears(4)));
        card.setCardStatusType(true);
        card.setAccountId(account);
        card.setCustomerId(customer);
        card.setCardPinHash(passwordEncoder.encode(pin)); // Güvenli şifreleme

        return cardRepository.save(card);
    }

    private long generateRandomCardNumber() {
        long number = 10000000000L + (long)(Math.random() * 89999999999L); // 11 haneli
        return number;
    }
    
    
    
    

    private int generateRandomCVV() {
        return (int) (Math.random() * 900) + 100; // 100–999 arası 3 haneli CVV
    }

}
