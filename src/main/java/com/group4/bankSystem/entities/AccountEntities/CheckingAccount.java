package com.group4.bankSystem.entities.AccountEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "CheckingAccount")
public class CheckingAccount {

    @Id
    @Column(name = "account_ID")
    private Integer accountId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_ID")
    private Account account;

    @Column(name = "checking_Balance", nullable = false)
    private Float checkingBalance;

    // --- Constructors ---

    public CheckingAccount() {}

    public CheckingAccount(Account account, Float checkingBalance) {
        this.account = account;
        this.checkingBalance = checkingBalance;
    }

    // --- Getters and Setters ---

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Float getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(Float checkingBalance) {
        this.checkingBalance = checkingBalance;
    }
}

