package com.group4.bankSystem.repository.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.TransactionEntities.RecurringTransaction;

public interface RecurringTransactionRepository extends JpaRepository<RecurringTransaction, Integer>{

}
