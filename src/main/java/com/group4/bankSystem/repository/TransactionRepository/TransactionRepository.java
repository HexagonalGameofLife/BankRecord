package com.group4.bankSystem.repository.TransactionRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.TransactionEntities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    List<Transaction> findByAccountIdIn(List<Integer> accountIds);

    void deleteByAccountIdIn(List<Integer> accountIds);

}
