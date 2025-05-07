package com.group4.bankSystem.controller.TransactionController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.bankSystem.dto.TransactionDto;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.services.TransactionServices.TransactionServices;

import com.group4.bankSystem.dto.CreateTransactionRequest;


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
    public List<TransactionDto> getTransactionsByCustomerId(@PathVariable Integer customerId) {
    return transactionServices.getTransactionsDtoByCustomerId(customerId);
}

    // Yeni bir işlem ekle
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionServices.addTransaction(transaction);
    }

    @PostMapping("/process")
    public Transaction processTransaction(@RequestBody CreateTransactionRequest request) {
      return transactionServices.processTransaction(request);
  }

}
