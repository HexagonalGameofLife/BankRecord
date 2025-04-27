package com.group4.bankSystem.entities.AccountEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "OtherCurrencyAccount")
@PrimaryKeyJoinColumn(name = "account_ID") // Hem PK hem FK
public class OtherCurrencyAccount extends Account {

    @Column(name = "currency_Type", nullable = false)
    private Integer currencyType; // İstersen Enum'a da bağlayabiliriz.

    @Column(name = "other_Currency_Balance", nullable = false)
    private Float otherCurrencyBalance;

    // --- Constructors ---

    public OtherCurrencyAccount() {}

    public OtherCurrencyAccount(Integer currencyType, Float otherCurrencyBalance) {
        this.currencyType = currencyType;
        this.otherCurrencyBalance = otherCurrencyBalance;
    }

    // --- Getters and Setters ---

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public Float getOtherCurrencyBalance() {
        return otherCurrencyBalance;
    }

    public void setOtherCurrencyBalance(Float otherCurrencyBalance) {
        this.otherCurrencyBalance = otherCurrencyBalance;
    }
}
