package com.group4.bankSystem.repository.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.TransactionEntities.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer>{
    
}
