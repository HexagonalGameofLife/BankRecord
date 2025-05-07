package com.group4.bankSystem.dto;

public class CardApplicationRequest {
    private Long accountId;
    private String pin;
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
}
