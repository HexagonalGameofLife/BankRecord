/*package com.group4.bankSystem.services.CustomerServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
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
    public void deleteCustomer(Integer customerId) {
        // 1) Silinecek müşteriyi al
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2) Müşterinin üye olduğu tüm hesapları al
        List<Account> accounts = userListRepository.findAccountsByCustomerId(customerId);

        for (Account account : accounts) {
            Integer accId = account.getAccountId();

            // 3) Hesaptaki tüm üyelik kayıtları
            List<UserList> members = userListRepository.findByAccount_AccountId(accId);

            if (members.size() > 1) {
                // —— PAYLAŞIMLI HESAP ——
                // 4a) Eğer silinen kullanıcı birincil ise, başka bir üyeyi devret
                if (account.getPrimaryUserId().equals(customerId)) {
                    members.stream()
                           .map(UserList::getCustomer)
                           .map(Customer::getCustomerId)
                           .filter(id -> !id.equals(customerId))
                           .findFirst()
                           .ifPresent(newPrimaryId -> {
                               account.setPrimaryUserId(newPrimaryId);
                               accountRepository.save(account);
                           });
                }
                // 4b) Sadece o müşterinin UserList kaydını sil (hesabı ve diğer üyeleri dokunmadan)
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);

            } else {
                // —— TEK ÜYELİ HESAP ——
                // 5a) Üyelik kaydını sil
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);
                // 5b) İsteğe bağlı: bu hesaba ait işlemler silinir...
                transactionRepository.deleteByAccountIdsInBothFromAndTo(List.of(accId));
                // 5c) Hesabı kaldır
                accountRepository.deleteById(accId);
            }
        }

        // 6) Son olarak müşteri kaydını sil
        customerRepository.deleteById(customerId);
    }

    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
      return customerRepository.findById(id);
    }

}*/

package com.group4.bankSystem.services.CustomerServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
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
    public void deleteCustomer(Integer customerId) {
        // 1) Silinecek müşteriyi al
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2) Müşterinin üye olduğu tüm hesapları al
        List<Account> accounts = userListRepository.findAccountsByCustomerId(customerId);

        for (Account account : accounts) {
            Integer accId = account.getAccountId();

            // 3) Hesaptaki tüm üyelik kayıtları
            List<UserList> members = userListRepository.findByAccount_AccountId(accId);

            if (members.size() > 1) {
                // —— PAYLAŞIMLI HESAP ——
                // 4a) Eğer silinen kullanıcı birincil ise, başka bir üyeyi devret
                if (account.getPrimaryUserId().equals(customerId)) {
                    members.stream()
                           .map(UserList::getCustomer)
                           .map(Customer::getCustomerId)
                           .filter(id -> !id.equals(customerId))
                           .findFirst()
                           .ifPresent(newPrimaryId -> {
                               account.setPrimaryUserId(newPrimaryId);
                               accountRepository.save(account);
                           });
                }
                // 4b) Sadece o müşterinin UserList kaydını sil (hesabı ve diğer üyeleri dokunmadan)
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);

            } else {
                // —— TEK ÜYELİ HESAP ——
                // 5a) Üyelik kaydını sil
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);
                // 5b) İsteğe bağlı: bu hesaba ait işlemler silinir...
                transactionRepository.deleteByAccountIdsInBothFromAndTo(List.of(accId));
                // 5c) Hesabı kaldır
                accountRepository.deleteById(accId);
            }
        }

        // 6) Son olarak müşteri kaydını sil
        customerRepository.deleteById(customerId);
    }

    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
      return customerRepository.findById(id);
    }

}*/

package com.group4.bankSystem.services.CustomerServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
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
    public void deleteCustomer(Integer customerId) {
        // 1) Silinecek müşteriyi al
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2) Müşterinin üye olduğu tüm hesapları al
        List<Account> accounts = userListRepository.findAccountsByCustomerId(customerId);

        for (Account account : accounts) {
            Integer accId = account.getAccountId();

            // 3) Hesaptaki tüm üyelik kayıtları
            List<UserList> members = userListRepository.findByAccount_AccountId(accId);

            if (members.size() > 1) {
                // —— PAYLAŞIMLI HESAP ——
                // 4a) Eğer silinen kullanıcı birincil ise, başka bir üyeyi devret
                if (account.getPrimaryUserId().equals(customerId)) {
                    members.stream()
                           .map(UserList::getCustomer)
                           .map(Customer::getCustomerId)
                           .filter(id -> !id.equals(customerId))
                           .findFirst()
                           .ifPresent(newPrimaryId -> {
                               account.setPrimaryUserId(newPrimaryId);
                               accountRepository.save(account);
                           });
                }
                // 4b) Sadece o müşterinin UserList kaydını sil (hesabı ve diğer üyeleri dokunmadan)
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);

            } else {
                // —— TEK ÜYELİ HESAP ——
                // 5a) Üyelik kaydını sil
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);
                // 5b) İsteğe bağlı: bu hesaba ait işlemler silinir...
                transactionRepository.deleteByAccountIdsInBothFromAndTo(List.of(accId));
                // 5c) Hesabı kaldır
                accountRepository.deleteById(accId);
            }
        }

        // 6) Son olarak müşteri kaydını sil
        customerRepository.deleteById(customerId);
    }

    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
      return customerRepository.findById(id);
    }

}*/

package com.group4.bankSystem.services.CustomerServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
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
    public void deleteCustomer(Integer customerId) {
        // 1) Silinecek müşteriyi al
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2) Müşterinin üye olduğu tüm hesapları al
        List<Account> accounts = userListRepository.findAccountsByCustomerId(customerId);

        for (Account account : accounts) {
            Integer accId = account.getAccountId();

            // 3) Hesaptaki tüm üyelik kayıtları
            List<UserList> members = userListRepository.findByAccount_AccountId(accId);

            if (members.size() > 1) {
                // —— PAYLAŞIMLI HESAP ——
                // 4a) Eğer silinen kullanıcı birincil ise, başka bir üyeyi devret
                if (account.getPrimaryUserId().equals(customerId)) {
                    members.stream()
                           .map(UserList::getCustomer)
                           .map(Customer::getCustomerId)
                           .filter(id -> !id.equals(customerId))
                           .findFirst()
                           .ifPresent(newPrimaryId -> {
                               account.setPrimaryUserId(newPrimaryId);
                               accountRepository.save(account);
                           });
                }
                // 4b) Sadece o müşterinin UserList kaydını sil (hesabı ve diğer üyeleri dokunmadan)
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);

            } else {
                // —— TEK ÜYELİ HESAP ——
                // 5a) Üyelik kaydını sil
                userListRepository.deleteByAccount_AccountIdAndCustomer_CustomerId(accId, customerId);
                // 5b) İsteğe bağlı: bu hesaba ait işlemler silinir...
                transactionRepository.deleteByAccountIdsInBothFromAndTo(List.of(accId));
                // 5c) Hesabı kaldır
                accountRepository.deleteById(accId);
            }
        }

        // 6) Son olarak müşteri kaydını sil
        customerRepository.deleteById(customerId);
    }

    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
      return customerRepository.findById(id);
    }

}




