package com.group4.bankSystem.dto;

import java.time.LocalDate;

public class TransactionDto {
    private String fromIban;
    private String toIban;
    private Float transactionAmount;
    private LocalDate transactionDate;
    private String description;

    // Constructor
    public TransactionDto(String fromIban, String toIban, Float transactionAmount, LocalDate transactionDate, String description) {
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    // Getters
    public String getFromIban() { return fromIban; }
    public String getToIban() { return toIban; }
    public Float getTransactionAmount() { return transactionAmount; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public String getDescription() { return description;}
}