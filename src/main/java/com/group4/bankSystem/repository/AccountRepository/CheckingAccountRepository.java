package com.group4.bankSystem.repository.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.bankSystem.entities.AccountEntities.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer>{

}
