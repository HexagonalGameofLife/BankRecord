package com.group4.bankSystem.entities.CustomerEntities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_ID")
    private Integer customerId;

    @Column(name = "customer_TC", unique = true, length = 11, nullable = false)
    private String customerTC;

    @Column(name = "customer_Name", length = 20, nullable = false)
    private String customerName;

    @Column(name = "customer_Surname", length = 20, nullable = false)
    private String customerSurname;

    @Column(name = "customer_Birthdate", nullable = false)
    private LocalDate customerBirthdate;

    @Column(name = "customer_Gender", length = 5)
    private String customerGender;

    @Column(name = "customer_PhoneNumber", unique = true, length = 15, nullable = false)
    private String customerPhoneNumber;

    @Column(name = "customer_Email", unique = true, length = 50, nullable = false)
    private String customerEmail;

    @Column(name = "customer_Address", length = 75)
    private String customerAddress;

    @Column(name = "customer_Registration_Date")
    private LocalDate customerRegistrationDate;

    @Column(name = "login_Password_Hash", length = 20, nullable = false)
    private String loginPasswordHash;

    @Column(name = "customer_income")
    private Integer customerIncome;

    @Column(name = "current_limit")
    private Double currentLimit;

    @Column(name = "current_debt")
    private Double currentDebt;

    // --- Constructors ---

    public Customer() {
    }

    // --- Getters and Setters ---

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTC() {
        return customerTC;
    }

    public void setCustomerTC(String customerTC) {
        this.customerTC = customerTC;
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

    public LocalDate getCustomerBirthdate() {
        return customerBirthdate;
    }

    public void setCustomerBirthdate(LocalDate customerBirthdate) {
        this.customerBirthdate = customerBirthdate;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDate getCustomerRegistrationDate() {
        return customerRegistrationDate;
    }

    public void setCustomerRegistrationDate(LocalDate customerRegistrationDate) {
        this.customerRegistrationDate = customerRegistrationDate;
    }

    public String getLoginPasswordHash() {
        return loginPasswordHash;
    }

    public void setLoginPasswordHash(String loginPasswordHash) {
        this.loginPasswordHash = loginPasswordHash;
    }

    public Integer getCustomerIncome() {
        return customerIncome;
    }

    public void setCustomerIncome(Integer customerIncome) {
        this.customerIncome = customerIncome;
    }

    public Double getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(Double currentLimit) {
        this.currentLimit = currentLimit;
    }

    public Double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(Double currentDebt) {
        this.currentDebt = currentDebt;
    }
}
