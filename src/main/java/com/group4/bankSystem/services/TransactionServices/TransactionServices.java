package com.group4.bankSystem.services.TransactionServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.group4.bankSystem.dto.TransactionDto;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;

@Service
public class TransactionServices {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction addTransaction(Transaction tx) {
      return transactionRepository.save(tx);
  }

    // 1. Tüm işlemleri getir (isteğe göre bütün işlemler veya belirli müşterinin işlemleri)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<TransactionDto> getTransactionsDtoByCustomerId(Integer customerId) {
    List<Integer> accountIds = accountRepository.findAccountIdsByCustomerId(customerId);
    List<Transaction> transactions = transactionRepository.findByAccountIdsInBothFromAndTo(accountIds);

    return transactions.stream()
    .map(tx -> new TransactionDto(
        tx.getFromAccount() != null ? tx.getFromAccount().getIban() : "-",
        tx.getToAccount()   != null ? tx.getToAccount().getIban()   : "-",
        tx.getTransactionAmount(),
        tx.getTransactionDate(),
        tx.getDescription()            // ← DTO’nın beklediği 5. parametre
    ))
    .collect(Collectors.toList());
  }
}

