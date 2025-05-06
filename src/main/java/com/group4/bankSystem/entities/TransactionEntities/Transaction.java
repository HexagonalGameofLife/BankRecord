package com.group4.bankSystem.entities.TransactionEntities;

<<<<<<< HEAD
=======
import jakarta.persistence.*;
>>>>>>> smhavci/Semih-Son
import java.time.LocalDate;

import com.group4.bankSystem.entities.AccountEntities.Account;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

=======
>>>>>>> smhavci/Semih-Son
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_ID")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "from_Account_ID", referencedColumnName = "account_ID", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_Account_ID", referencedColumnName = "account_ID", nullable = false)
    private Account toAccount;

    @Column(name = "transaction_Type", nullable = false)
    private Integer transactionType;

    @Column(name = "transaction_Amount", nullable = false)
    private Float transactionAmount;

    @Column(name = "transaction_Date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "description", length = 50)
    private String description;

    // --- Constructors ---

    public Transaction() {}

    // --- Getters and Setters ---

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getFromAccountId() {
        return fromAccount.getAccountId();
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccount.setAccountId(fromAccountId);
    }

    public Integer getToAccountId() {
        return toAccount.getAccountId();
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccount.setAccountId(toAccountId);
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
<<<<<<< HEAD

    public Account getFromAccount() {
      return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

=======
>>>>>>> smhavci/Semih-Son
}
