package com.group4.bankSystem.entities.TransactionEntities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "ExchangeRate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_Rate_ID")
    private Integer exchangeRateId;

    @Column(name = "base_Currency", nullable = false)
    private Integer baseCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_Currency", nullable = false, length = 12)
    private CurrencyEnum targetCurrency; // ENUM yapmazsak String tutacağız (USD, EUR vs.)

    @Column(name = "rate_to_Try", nullable = false)
    private Float rateToTry;

    @Column(name = "rate_Date", nullable = false)
    private LocalDate rateDate;


    public ExchangeRate() {}

    public Integer getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Integer exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public CurrencyEnum  getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
      this.targetCurrency = CurrencyEnum.valueOf(targetCurrency.getCurrencyCode());
    }

    public Float getRateToTry() {
        return rateToTry;
    }

    public void setRateToTry(Float rateToTry) {
        this.rateToTry = rateToTry;
    }

    public LocalDate getRateDate() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate = rateDate;
    }
}
