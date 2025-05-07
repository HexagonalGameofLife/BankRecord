package com.group4.bankSystem.services.CustomerServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.TransactionEntities.Transaction;
import com.group4.bankSystem.repository.AccountRepository.AccountRepository;
import com.group4.bankSystem.repository.CustomerRepository.CustomerRepository;
import com.group4.bankSystem.repository.CustomerRepository.UserListRepository;
import com.group4.bankSystem.repository.TransactionRepository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void changeCustomerName(Integer customerId, String newName) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerName(newName); // burada setName metodunun olması lazım Customer içinde!
        customerRepository.save(customer);
    }

    public void changeCustomerSurname(Integer customerId, String newSurname) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerSurname(newSurname);
        customerRepository.save(customer);
    }

    public void changeCustomerPassword(Integer customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        if (newPassword.equals(customer.getLoginPasswordHash())) {
            throw new RuntimeException("New password cannot be the same as the old password.");
        }
        customer.setLoginPasswordHash(newPassword);
        customerRepository.save(customer);
    }

    public void changeCustomerAddress(Integer customerId, String newAddress) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerAddress(newAddress);
        customerRepository.save(customer);
    }

    public void changeCustomerPhoneNumber(Integer customerId, String newPhoneNumber) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerPhoneNumber(newPhoneNumber);
        customerRepository.save(customer);
    }

    public void changeCustomerEmail(Integer customerId, String newEmail) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerEmail(newEmail);
        customerRepository.save(customer);
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

    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer customerId) {
      customerRepository.deleteById(customerId);
    }

    public Optional<Customer> getCustomerById(Integer id) {
      return customerRepository.findById(id);
    }

}
