package com.group4.bankSystem.entities.AccountEntities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CreditCardAccount extends Account {

    @Column(name = "credit_Card_Limit", nullable = false)
    private Float creditCardLimit;

    @Column(name = "current_Dept", nullable = false)
    private Float currentDept;

    @Column(name = "due_Date", nullable = false)
    private LocalDate dueDate;

    // --- Constructors ---

    public CreditCardAccount() {}

    public CreditCardAccount(Float creditCardLimit, Float currentDept, LocalDate dueDate) {
        this.creditCardLimit = creditCardLimit;
        this.currentDept = currentDept;
        this.dueDate = dueDate;
    }

    // --- Getters and Setters ---

    public Float getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Float creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Float getCurrentDept() {
        return currentDept;
    }

    public void setCurrentDept(Float currentDept) {
        this.currentDept = currentDept;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
