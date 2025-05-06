package com.group4.bankSystem.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.AccountEntities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

<<<<<<< HEAD
  @Query("SELECT a.accountId FROM Account a WHERE a.primaryUserId = :customerId")
  List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);

=======
    @Query("SELECT a.accountId FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);
>>>>>>> smhavci/Semih-Son

    List<Account> findAll();

    Optional<Account> findByIban(String iban);
}
