package com.example.issuer.dto;

public class PaymentRequest {
    private String tokenValue;
    private Double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String tokenValue, Double amount) {
        this.tokenValue = tokenValue;
        this.amount = amount;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "PaymentRequest{" +
                "tokenValue='" + tokenValue + '\'' +
                ", amount=" + amount +
                '}';
    }
}
