package com.group4.bankSystem.entities.AccountEntities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group4.bankSystem.entities.CustomerEntities.UserList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_ID")
    private Integer accountId;

    @Column(name = "primary_user_id", nullable = false)
    private Integer primaryUserId; // Customer entity ile tam ilişki kurmak istersen ManyToOne yapabilirsin.

    @Column(name = "status_ID", nullable = false)
    private Integer statusId;

    @Column(name = "IBAN", unique = true, length = 34, nullable = false)
    private String iban;

    @Column(name = "account_Type_ID", nullable = false)
    private Integer accountTypeId;

    @Column(name = "account_Start_Date", nullable = false)
    private LocalDate accountStartDate;

    @Column(name = "overdraft_Enabled", nullable = false)
    private Boolean overdraftEnabled;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<UserList> userList;



    // --- Constructors ---

    public Account() {}

    public Account(Integer primaryUserId, Integer statusId, String iban, Integer accountTypeId, LocalDate accountStartDate, Boolean overdraftEnabled) {
        this.primaryUserId = primaryUserId;
        this.statusId = statusId;
        this.iban = iban;
        this.accountTypeId = accountTypeId;
        this.accountStartDate = accountStartDate;
        this.overdraftEnabled = overdraftEnabled;
    }

    // --- Getters and Setters ---

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(Integer primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public LocalDate getAccountStartDate() {
        return accountStartDate;
    }

    public void setAccountStartDate(LocalDate accountStartDate) {
        this.accountStartDate = accountStartDate;
    }

    public Boolean getOverdraftEnabled() {
        return overdraftEnabled;
    }

    public void setOverdraftEnabled(Boolean overdraftEnabled) {
        this.overdraftEnabled = overdraftEnabled;
    }
    public List<UserList> getUserList() {
        return userList;
    }
    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }

    //Account-List.html için oluşturuldu. geçici olarak tutuyor db'ye dokunmuyor.
    @Transient
    public String getAccountType() {
        return switch (accountTypeId) {
            case 1 -> "Savings";
            case 2 -> "Checking";
            case 3 -> "CreditCard";
            case 4 -> "Investment";
            default -> "Unknown";
    };
  }

}
