package com.group4.bankSystem.entities.TransactionEntities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RecurringTransaction")
public class RecurringTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurring_Transaction_ID")
    private Integer recurringTransactionId;

    @Column(name = "recurrence_Type", nullable = false)
    private Integer recurrenceType;

    @Column(name = "start_Date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_Date", nullable = false)
    private LocalDate endDate;

    @Column(name = "next_Run_Date", nullable = false)
    private LocalDate nextRunDate;

    // --- Constructors ---

    public RecurringTransaction() {}

    public RecurringTransaction(Integer recurrenceType, LocalDate startDate, LocalDate endDate, LocalDate nextRunDate) {
        this.recurrenceType = recurrenceType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nextRunDate = nextRunDate;
    }

    // --- Getters and Setters ---

    public Integer getRecurringTransactionId() {
        return recurringTransactionId;
    }

    public void setRecurringTransactionId(Integer recurringTransactionId) {
        this.recurringTransactionId = recurringTransactionId;
    }

    public Integer getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(Integer recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(LocalDate nextRunDate) {
        this.nextRunDate = nextRunDate;
    }
}
