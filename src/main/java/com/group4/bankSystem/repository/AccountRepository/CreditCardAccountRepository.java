package com.group4.bankSystem.repository.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.AccountEntities.CreditCardAccount;

public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Integer>{
    
}
