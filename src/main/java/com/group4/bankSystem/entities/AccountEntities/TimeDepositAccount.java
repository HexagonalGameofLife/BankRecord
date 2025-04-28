package com.group4.bankSystem.entities.AccountEntities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TimeDepositAccount extends Account {

    @Column(name = "maturity_Date", nullable = false)
    private LocalDate maturityDate;

    @Column(name = "deposit_Date", nullable = false)
    private LocalDate depositDate;

    @Column(name = "interest_Rate", nullable = false)
    private Float interestRate;

    @Column(name = "time_Deposit_Balance", nullable = false)
    private Float timeDepositBalance;

    // --- Constructors ---

    public TimeDepositAccount() {}

    public TimeDepositAccount(LocalDate maturityDate, LocalDate depositDate, Float interestRate, Float timeDepositBalance) {
        this.maturityDate = maturityDate;
        this.depositDate = depositDate;
        this.interestRate = interestRate;
        this.timeDepositBalance = timeDepositBalance;
    }

    // --- Getters and Setters ---

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Float getTimeDepositBalance() {
        return timeDepositBalance;
    }

    public void setTimeDepositBalance(Float timeDepositBalance) {
        this.timeDepositBalance = timeDepositBalance;
    }
}
