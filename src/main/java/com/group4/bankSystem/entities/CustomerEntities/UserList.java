package com.group4.bankSystem.entities.CustomerEntities;

import com.group4.bankSystem.entities.AccountEntities.Account;

import jakarta.persistence.*;


@Entity
@Table(name= "UserList")
public class UserList {
    
    @EmbeddedId
    private UserListId id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name= "customer_ID")
    private Customer customer;


    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name= "account_ID")
    private Account account;  

    @Column(name= "is_Primary_User", nullable = false)
    private boolean isPrimaryUser;

    // --- Getters and Setters ---

    public UserListId getId() {
        return id;
    }

    public void setId(UserListId id) {
        this.id = id;
    }

    public boolean isPrimaryUser() {
        return isPrimaryUser;
    }

    public void setPrimaryUser(boolean isPrimaryUser) {
        this.isPrimaryUser = isPrimaryUser;
    }

    public Customer getCustomer() {  // 🌟 Getter eklendi
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {    // 🌟 Getter eklendi
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

