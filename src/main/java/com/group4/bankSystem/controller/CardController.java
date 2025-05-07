package com.group4.bankSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.bankSystem.dto.CardApplicationRequest;
import com.group4.bankSystem.services.CardService;



@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<?> applyForCard(@RequestBody CardApplicationRequest request) {
        try {
            cardService.createCardForAccount(request.getAccountId(), request.getPin());
            return ResponseEntity.ok("Card created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Console'a detaylı hata yazacak
            return ResponseEntity.status(500).body("Card creation failed: " + e.getMessage());
        }
    }

}



