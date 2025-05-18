package com.group4.bankSystem.services.CustomerServices;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.CustomerEntities.Admin;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.CustomerRepository.AdminRepository;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.CustomerRepository.UserListRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final UserListRepository userListRepository;
    private final TransactionRepository transactionRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository,
                        CustomerRepository customerRepository,
                        AccountRepository accountRepository,
                        UserListRepository userListRepository,
                        TransactionRepository transactionRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.userListRepository = userListRepository;
        this.transactionRepository = transactionRepository;
    }

    public Admin login(String username, String rawPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (passwordEncoder.matches(rawPassword, admin.getPasswordHash())) {
                return admin;
            }
        }
        return null;
    }

    public boolean saveAdmin(Admin admin, String confirmPassword) {
        if (!admin.getPasswordHash().equals(confirmPassword)) {
            throw new IllegalArgumentException("Şifreler uyuşmuyor.");
        }

        if (!admin.getPasswordHash().matches("\\d{6}")) {
            throw new IllegalArgumentException("Şifre sadece 6 haneli rakamlardan oluşmalıdır.");
        }

        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            return false;
        }

        admin.setPasswordHash(passwordEncoder.encode(admin.getPasswordHash()));
        adminRepository.save(admin);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void deleteCustomerAndRelatedData(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Integer> accountIds = userListRepository.findAccountIdsByCustomerId(customerId);

        if (!accountIds.isEmpty()) {
            List<Transaction> transactions = transactionRepository.findByAccountIdsInBothFromAndTo(accountIds);
            transactionRepository.deleteAll(transactions);
        }

        userListRepository.deleteByCustomer_CustomerId(customerId);

        if (!accountIds.isEmpty()) {
            accountRepository.deleteAllById(accountIds);
        }

        customerRepository.deleteById(customerId);
    }
}
