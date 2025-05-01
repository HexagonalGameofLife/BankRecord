package com.group4.bankSystem.repository.dto;

public class LoginRequest {
    private String customerTc;
    private String password;

    // Getter ve Setter'lar

    public String getCustomerTc() {
        return customerTc;
    }

    public void setCustomerTc(String customerTc) {
        this.customerTc = customerTc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
