package com.group4.bankSystem.dto;

import java.util.List;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.AccountEntities.Card;
import com.group4.bankSystem.entities.CustomerEntities.Customer;

public class CustomerWithAccountsDTO {

    private Integer customerId;
    private String customerName;
    private String customerSurname;
    private Integer customerIncome;
    private Double customerDebt;
    private List<Account> accounts;
    private List<Card> cards;


    public CustomerWithAccountsDTO(Customer customer, List<Account> accounts) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.customerSurname = customer.getCustomerSurname();
        this.customerIncome = customer.getCustomerIncome();
        this.customerDebt = customer.getCurrentDebt();
        this.accounts = accounts;
        this.cards = customer.getCards(); // Eğer müşteri kartları entity'de varsa

    }

    // Getters and setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Integer getCustomerIncome() {
        return customerIncome;
    }

    public void setCustomerIncome(Integer customerIncome) {
        this.customerIncome = customerIncome;
    }

    public Double getCustomerDebt() {
        return customerDebt;
    }

    public void setCustomerDebt(Double customerDebt) {
        this.customerDebt = customerDebt;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}


