package com.group4.bankSystem.entities.CustomerEntities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//because the primary key is a composite key, we need to define it as a one class
@Embeddable
public class UserListId implements Serializable {

    private Integer customerId;
    private Integer accountId;

    public UserListId() {}

    public UserListId(Integer customerId, Integer accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }

    // Getters & Setters
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

    // equals() ve hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserListId)) return false;
        UserListId that = (UserListId) o;
        return Objects.equals(customerId, that.customerId) &&
               Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, accountId);
    }
}

