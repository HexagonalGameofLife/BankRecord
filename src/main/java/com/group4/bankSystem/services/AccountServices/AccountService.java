package com.group4.bankSystem.services.AccountServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    // bir hesabın tum kullanicilari
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

    public List<Account> getAccountsForCustomer(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId);
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

    // hesap 3 kisiden azsa musteri ekleme
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
        userList.setAccount(account);
        userList.setCustomer(customer);
        userListRepository.save(userList);
    }

    public Account createAccountFromDto(CreateAccountRequest request) {
        Account account;

        switch (request.getAccountType().toLowerCase()) {
            case "checking" -> {
                CheckingAccount ca = new CheckingAccount();
                ca.setCheckingBalance((float) request.getBalance());
                fillCommonFields(ca, request);
                account = accountRepository.save(ca); // Kaydettikten sonra ID oluşur
            }
            case "creditcard" -> {
                CreditCardAccount cc = new CreditCardAccount();
                cc.setCreditCardLimit((float) request.getBalance());
                cc.setCurrentDept(0f);
                cc.setDueDate(LocalDate.now().plusMonths(1));
                fillCommonFields(cc, request);
                account = accountRepository.save(cc);
            }
            case "savings", "investment", "timedeposit" -> {
                TimeDepositAccount ta = new TimeDepositAccount();
                ta.setTimeDepositBalance((float) request.getBalance());
                ta.setDepositDate(LocalDate.now());
                ta.setMaturityDate(LocalDate.now().plusMonths(6));
                ta.setInterestRate(0.15f);
                fillCommonFields(ta, request);
                account = accountRepository.save(ta);
            }
            case "othercurrency" -> {
                OtherCurrencyAccount oa = new OtherCurrencyAccount();
                oa.setOtherCurrencyBalance((float) request.getBalance());
                oa.setCurrencyType(1);
                fillCommonFields(oa, request);
                account = accountRepository.save(oa);
            }
            default -> throw new IllegalArgumentException("Unsupported account type: " + request.getAccountType());
        }

        // 🔁 Account kaydedildikten SONRA UserList oluşturulmalı
        Customer customer = customerRepository.findById(request.getCustomerId().intValue())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        UserList userList = new UserList();
        UserListId id = new UserListId();
        id.setCustomerId(customer.getCustomerId());
        id.setAccountId(account.getAccountId()); // ✅ Artık null değil!
        userList.setId(id);
        userList.setAccount(account);
        userList.setCustomer(customer);
        userList.setPrimaryUser(true);

        userListRepository.save(userList);

        return account;
    }

    private void fillCommonFields(Account account, CreateAccountRequest request) {
        account.setIban(generateFakeIban());
        account.setAccountTypeId(convertAccountTypeToId(request.getAccountType()));
        account.setAccountStartDate(LocalDate.now());
        account.setOverdraftEnabled(false);
        account.setPrimaryUserId(request.getCustomerId().intValue());
        account.setStatusId(1);
    }

    private String generateFakeIban() {
        String number = String.format("%016d", (long) (Math.random() * 1_000_000_000_000_000L));
        return "TR" + number;
    }

    private int convertAccountTypeToId(String accountType) {
        return switch (accountType.toLowerCase()) {
            case "savings" -> 1;
            case "checking" -> 2;
            case "creditcard" -> 3;
            case "investment" -> 4;
            default -> throw new IllegalArgumentException("Unknown account type: " + accountType);
        };
    }
}
