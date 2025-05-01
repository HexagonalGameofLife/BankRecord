package com.group4.bankSystem.repository.AccountRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.AccountEntities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("SELECT a.accountId FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);
}
