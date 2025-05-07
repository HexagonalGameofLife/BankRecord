package com.group4.bankSystem.dto;

public class CreateTransactionRequest {
  private String fromIban;    // Gönderenin IBAN’ı (biz DB’den çekeceğiz, yine de DTO’da tutalım)
  private String toIban;      // Alıcının IBAN’ı (manuel girilecek)
  private Float amount;       // Gönderilecek tutar
  private String description; // İsteğe bağlı açıklama

  // Default constructor
  public CreateTransactionRequest() {}

  // Getter/Setter
  public String getFromIban() { return fromIban; }
  public void setFromIban(String fromIban) { this.fromIban = fromIban; }
  public String getToIban() { return toIban; }
  public void setToIban(String toIban) { this.toIban = toIban; }
  public Float getAmount() { return amount; }
  public void setAmount(Float amount) { this.amount = amount; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}
