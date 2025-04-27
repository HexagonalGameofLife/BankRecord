package com.group4.bankSystem.entities.AccountEntities;

import java.sql.Date;
import java.util.Set;

import com.group4.bankSystem.entities.CustomerEntities.Customer;

import jakarta.persistence.*;



@Entity
public class Card {
    
    @Id
    @Column(name = "card_ID", unique = true, length = 11, nullable = false)
    private long cardNumber;

    @ManyToOne //baska entityden gelen alanlarin iliskisi yazilir. 
    @Column(name = "customer_ID", unique = true, length = 11, nullable = false)
    private Customer customerId;

    @ManyToOne //bu da oyle
    @Column(name = "account_ID", unique = true, length = 11, nullable = false)
    private Account accountId;

    @Column(name = "Card_Status_Type", nullable = false)
    private boolean CardStatusType; //aktif mi degil mi

    @Column(name = "exprire_Date", nullable = false)
    private Date expireDate;

    @Column(name = "cvv", length = 3, nullable = false)
    private int cvv;

    @Column(name = "customer_TC", unique = true, length = 11, nullable = false)
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
