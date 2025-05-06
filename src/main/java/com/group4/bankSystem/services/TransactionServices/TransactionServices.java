package com.group4.bankSystem.services.TransactionServices;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.dto.TransactionDto;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;
=======
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> smhavci/Semih-Son

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

<<<<<<< HEAD
    public List<TransactionDto> getTransactionsDtoByCustomerId(Integer customerId) {
    List<Integer> accountIds = accountRepository.findAccountIdsByCustomerId(customerId);
    List<Transaction> transactions = transactionRepository.findByAccountIdsInBothFromAndTo(accountIds);

    return transactions.stream().map(tx -> new TransactionDto(
            tx.getFromAccount() != null ? tx.getFromAccount().getIban() : "-",
            tx.getToAccount() != null ? tx.getToAccount().getIban() : "-",
            tx.getTransactionAmount(),
            tx.getTransactionDate(),
            tx.getDescription()
    )).toList();
}
=======
    public List<Transaction> getTransactionsByCustomerId(Integer customerId) {
        // Burada önce müşterinin sahip olduğu accountları bulacağız
        List<Integer> accountIds = accountRepository.findAccountIdsByCustomerId(customerId);

        // Sonra o accountlara ait transactionları bulacağız
        return transactionRepository.findByAccountIdsInBothFromAndTo(accountIds);
      }
>>>>>>> smhavci/Semih-Son

    // 2. Yeni bir işlem ekle
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
