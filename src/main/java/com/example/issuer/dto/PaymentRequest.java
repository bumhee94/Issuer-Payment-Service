package com.example.issuer.dto;

import com.example.issuer.validation.ValidAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequest {
    private String tokenValue;
    @ValidAmount
    private double amount;
}

