package com.group4.bankSystem.controller.TransactionController;

import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.services.TransactionServices.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionServices transactionServices;

    // Tüm işlemleri getir
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionServices.getAllTransactions();
    }

    // Belirli bir müşterinin işlemlerini getir
    @GetMapping("/customer/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable Integer customerId) {
        return transactionServices.getTransactionsByCustomerId(customerId);
    }

    // Yeni bir işlem ekle
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionServices.addTransaction(transaction);
    }
}
