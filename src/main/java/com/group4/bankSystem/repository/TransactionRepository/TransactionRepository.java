package com.group4.bankSystem.repository.TransactionRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.TransactionEntities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    @Query("SELECT t FROM Transaction t WHERE t.fromAccount.accountId IN :accountIds OR t.toAccount.accountId IN :accountIds")
    List<Transaction> findByAccountIdsInBothFromAndTo(@Param("accountIds") List<Integer> accountIds);
}
