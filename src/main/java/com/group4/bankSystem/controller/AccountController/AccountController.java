package com.group4.bankSystem.controller.AccountController;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.CustomerRepository.UserListRepository;
import com.group4.bankSystem.services.AccountServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Tüm hesapları getir
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // Belirli bir hesabı ID'siyle getir
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Hesap oluştur
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    // Hesap sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        Optional<Account> account = accountService.getAccountById(id);
        if (account.isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Belirli bir hesabın kullanıcılarını getir
    @GetMapping("/{accountId}/users")
    public List<Customer> getAllUsersOfAccount(@PathVariable Integer accountId) {
        return accountService.getAllUsersOfAccount(accountId);
    }

    // Bir hesaba yeni kullanıcı ekle
    @PostMapping("/{accountId}/users/{customerId}")
    public ResponseEntity<String> addUserToAccount(@PathVariable Integer accountId,
                                                   @PathVariable Integer customerId,
                                                   @RequestParam(defaultValue = "false") boolean isPrimaryUser) {
        try {
            accountService.addUserToAccount(accountId, customerId, isPrimaryUser);
            return ResponseEntity.ok("User added to account successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/ibans")
    public List<Map<String, Object>> getAllIbans() {
        return accountRepository.findAll().stream()
                .map(account -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("accountId", account.getAccountId());
                    map.put("iban", account.getIban());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-iban/{iban}")
    public ResponseEntity<Account> findByIban(@PathVariable String iban) {
      return accountRepository.findByIban(iban)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/my-ibans")
  public List<Map<String, Object>> getMyIbans(@AuthenticationPrincipal Customer customer) {
      List<Account> accounts = userListRepository.findAccountsByCustomerId(customer.getCustomerId());

      return accounts.stream()
          .map(account -> {
              Map<String, Object> map = new HashMap<>();
              map.put("accountId", account.getAccountId());
              map.put("iban", account.getIban());
              return map;
          })
          .collect(Collectors.toList());
  }


}
