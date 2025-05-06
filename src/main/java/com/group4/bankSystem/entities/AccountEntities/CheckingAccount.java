package com.group4.bankSystem.entities.AccountEntities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "CheckingAccount")
public class CheckingAccount extends Account {

    @Column(name = "checking_Balance", nullable = false)
    private Float checkingBalance;

    // --- Constructors ---

    public CheckingAccount(Integer primaryUserId, Integer statusId, String iban, Integer accountTypeId,
                           LocalDate accountStartDate, Boolean overdraftEnabled, Float checkingBalance) {
        super(primaryUserId, statusId, iban, accountTypeId, accountStartDate, overdraftEnabled);
        this.checkingBalance = checkingBalance;
    }

    // --- Getters and Setters ---

    public Float getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(Float checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public CheckingAccount() {
      super(); // Account sınıfının parametresiz constructor'ını çağırır
  }

}

