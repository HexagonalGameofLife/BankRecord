package com.group4.bankSystem.dto;

public class CreateAccountRequest {
    private String accountType;
    private double balance;
    private Long customerId; // 🔥 eklendi: UserList için gerekli
    private int customerIncome;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
