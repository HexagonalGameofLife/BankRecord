package com.group4.bankSystem.entities.AccountEntities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.bankSystem.entities.CustomerEntities.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Card {

    @Id
    @Column(name = "card_ID", unique = true, length = 11, nullable = false)
    private long cardNumber;

    @ManyToOne // başka entityden gelen alanların ilişkisi yazılır
    @JoinColumn(name = "customer_id", nullable = false) // Doğru kullanım: @JoinColumn
    private Customer customerId;

    @ManyToOne // başka entityden gelen alanların ilişkisi yazılır
    @JoinColumn(name = "account_id", nullable = false) // Doğru kullanım: @JoinColumn
    @JsonIgnoreProperties({"transactions", "customer", "accountType",})
    private Account accountId;

    @Column(name = "Card_Status_Type", nullable = false)
    private boolean CardStatusType; //aktif mi degil mi

    @Column(name = "exprire_Date", nullable = false)
    private Date expireDate;

    @Column(name = "cvv", length = 3, nullable = false)
    private int cvv;

    @Column(name = "customer_TC", unique = true, length = 60, nullable = false)
    private String cardPinHash;

    public Account getAccountId() {
        return accountId;
    }
    public long getCardNumber() {
        return cardNumber;
    }
    public String getCardPinHash() {
        return cardPinHash;
    }
    public Customer getCustomerId() {
        return customerId;
    }
    public int getCvv() {
        return cvv;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCardStatusType(boolean cardStatusType) {
        CardStatusType = cardStatusType;
    }
    public void setCardPinHash(String cardPinHash) {
        this.cardPinHash = cardPinHash;
    }
    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

}
