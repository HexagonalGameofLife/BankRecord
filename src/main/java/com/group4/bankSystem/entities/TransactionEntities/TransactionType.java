package com.group4.bankSystem.entities.TransactionEntities;

public enum TransactionType {
  TRANSFER(1),
  WITHDRAW(2),
  DEPOSIT(3),
  CREDIT_PAYMENT(4);

  private final int code;

  TransactionType(int code) {
      this.code = code;
  }

  public int getCode() {
      return code;
  }

  public static TransactionType fromCode(int code) {
      for (TransactionType type : values()) {
          if (type.code == code) return type;
      }
      throw new IllegalArgumentException("Geçersiz işlem tipi kodu: " + code);
  }
}
