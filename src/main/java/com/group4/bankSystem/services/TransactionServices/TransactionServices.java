package com.group4.bankSystem.services.TransactionServices;

import java.util.List;
<<<<<<< HEAD
import java.time.LocalDate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
=======
>>>>>>> upstream/master

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.dto.TransactionDto;
<<<<<<< HEAD
import com.group4.bankSystem.dto.CreateTransactionRequest;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.AccountEntities.CheckingAccount;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;


=======
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;

>>>>>>> upstream/master
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

  /**
 * Manuel IBAN transferi:
 *  fromIban’den amount düş, toIban’e amount ekle, işlem kaydı oluştur.
 */
@Transactional
public Transaction processTransaction(CreateTransactionRequest request) {
    // 1. Gönderen hesabı getir
    Account fromAcc = accountRepository.findByIban(request.getFromIban())
    .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Gönderen IBAN bulunamadı: " + request.getFromIban()));
        Account toAcc = accountRepository.findByIban(request.getToIban())
    .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Alıcı IBAN bulunamadı: "   + request.getToIban()));

    // 3. Sadece CheckingAccount desteklensin
    if (!(fromAcc instanceof CheckingAccount) || !(toAcc instanceof CheckingAccount)) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Sadece vadesiz (CheckingAccount) hesaplar arasında transfer yapılabilir."
        );
    }
    CheckingAccount sender   = (CheckingAccount) fromAcc;
    CheckingAccount receiver = (CheckingAccount) toAcc;

    // 4. Bakiye kontrolü
    float amt = request.getAmount();
    if (sender.getCheckingBalance() < amt) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Gönderen hesabın bakiyesi yetersiz."
        );
    }

    // 5. Bakiyeleri güncelle
    sender.setCheckingBalance(sender.getCheckingBalance() - amt);
    receiver.setCheckingBalance(receiver.getCheckingBalance() + amt);
    accountRepository.save(sender);
    accountRepository.save(receiver);

    // 6. Transaction kaydı oluştur
    Transaction tx = new Transaction();
    tx.setFromAccount(sender);
    tx.setToAccount(receiver);
    tx.setTransactionAmount(amt);
    tx.setTransactionDate(LocalDate.now());
    tx.setDescription(request.getDescription());
    return transactionRepository.save(tx);
}
}

