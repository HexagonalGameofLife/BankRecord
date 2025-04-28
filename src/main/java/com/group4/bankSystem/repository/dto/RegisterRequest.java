package com.group4.bankSystem.repository.dto;

public class RegisterRequest {

    private String customerTc;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhoneNumber;
    private String password;

    // Getter ve Setter'lar
    public String getCustomerTc() {
        return customerTc;
    }

    public void setCustomerTc(String customerTc) {
        this.customerTc = customerTc;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

