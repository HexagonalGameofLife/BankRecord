package com.group4.bankSystem.services.AccountServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
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
}
