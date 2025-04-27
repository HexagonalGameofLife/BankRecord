package com.group4.bankSystem.services.TransactionServices;

import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // 1. Tüm işlemleri getir (isteğe göre bütün işlemler veya belirli müşterinin işlemleri)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCustomerId(Integer customerId) {
        // Burada önce müşterinin sahip olduğu accountları bulacağız
        List<Integer> accountIds = accountRepository.findAccountIdsByCustomerId(customerId);

        // Sonra o accountlara ait transactionları bulacağız
        return transactionRepository.findByAccountIdIn(accountIds);
    }

    // 2. Yeni bir işlem ekle
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
