package com.example.issuer.dto;

import com.example.issuer.validation.ValidAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequest {
    private String tokenValue;
    @ValidAmount //0원 및 음수금액 유효성검사 어노테이션
    private double amount;
}

