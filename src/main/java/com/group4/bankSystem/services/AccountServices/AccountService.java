package com.group4.bankSystem.services.AccountServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.dto.CreateAccountRequest;
import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.AccountEntities.CheckingAccount;
import com.group4.bankSystem.entities.AccountEntities.CreditCardAccount;
import com.group4.bankSystem.entities.AccountEntities.OtherCurrencyAccount;
import com.group4.bankSystem.entities.AccountEntities.TimeDepositAccount;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
import com.group4.bankSystem.entities.CustomerEntities.UserListId;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.CustomerRepository.UserListRepository;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //bir hesabın tum kullanicilari
    public List<Customer> getAllUsersOfAccount(Integer accountId) {
        List<UserList> userLists = userListRepository.findByAccount_AccountId(accountId);

        return userLists.stream()
                .map(userList -> userList.getCustomer())
                .toList();
    }

    // Tüm hesapları getir
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Belirli bir müşteriye ait hesapları getir
    public List<Integer> getAccountsByCustomerId(Integer customerId) {
      return accountRepository.findAccountIdsByCustomerId(customerId);
    }

    // Tek bir hesabı ID'siyle getir
    public Optional<Account> getAccountById(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    // Yeni bir hesap kaydet
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    // Bir hesabı sil
    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }

    //hesap 3 kisiden azsa musteri ekleme
    public void addUserToAccount(Integer accountId, Integer customerId, boolean isPrimaryUser) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        int currentUserCount = account.getUserList() != null ? account.getUserList().size() : 0;

        if (currentUserCount >= 3) {
            throw new RuntimeException("This account already has 3 users. Cannot add more.");
        }

        UserList userList = new UserList();
        UserListId id = new UserListId();
        id.setAccountId(accountId);
        id.setCustomerId(customerId);
        userList.setId(id);
        userList.setPrimaryUser(isPrimaryUser);

        userListRepository.save(userList);
    }

    // CreateAccountRequest DTO'su için kuruldu.
    public Account createAccountFromDto(CreateAccountRequest request) {
    String type = request.getAccountType();
    float balance = (float) request.getBalance();

    Account account;

    switch (type) {
        case "Savings" -> {
            TimeDepositAccount acc = new TimeDepositAccount();
            acc.setTimeDepositBalance(balance);
            acc.setMaturityDate(LocalDate.now().plusYears(1));
            acc.setDepositDate(LocalDate.now());
            acc.setInterestRate(0.15f); // örnek faiz oranı
            account = acc;
        }
        case "Checking" -> {
            CheckingAccount acc = new CheckingAccount();
            acc.setCheckingBalance(balance);
            account = acc;
        }
        case "CreditCard" -> {
            CreditCardAccount acc = new CreditCardAccount();
            acc.setCreditCardLimit(balance);
            acc.setCurrentDept(0f);
            acc.setDueDate(LocalDate.now().plusMonths(1));
            account = acc;
        }
        case "Investment" -> {
            OtherCurrencyAccount acc = new OtherCurrencyAccount();
            acc.setOtherCurrencyBalance(balance);
            acc.setCurrencyType(1); // örnek: 1 = TL, 2 = USD, 3 = EUR
            account = acc;
        }
        default -> throw new IllegalArgumentException("Unknown account type: " + type);
    }

    // Ortak alanlar
    account.setIban(generateIban());
    account.setAccountStartDate(LocalDate.now());
    account.setOverdraftEnabled(false);
    account.setPrimaryUserId(1);
    account.setStatusId(1);
    account.setAccountTypeId(mapAccountTypeToId(type));

    return accountRepository.save(account);
}

      private String generateIban() {
          String countryCode = "TR";
          String bankCode = "00061"; // 5 hane
          String accountNumber = String.format("%017d", Math.abs(new Random().nextLong() % 1_000_000_000_000_000_00L)); // 17 hane
          String controlDigits = "00"; // geçici
          return countryCode + controlDigits + bankCode + accountNumber;
      }




private int mapAccountTypeToId(String type) {
  return switch (type) {
      case "Savings" -> 1;
      case "Checking" -> 2;
      case "CreditCard" -> 3;
      case "Investment" -> 4;
      default -> throw new IllegalArgumentException("Unknown account type: " + type);
  };
}

}
