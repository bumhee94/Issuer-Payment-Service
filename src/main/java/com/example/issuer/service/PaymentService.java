package com.example.issuer.service;

import com.example.issuer.entity.Payment;
import com.example.issuer.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * 결제 승인/거절 처리
     *
     * @param tokenValue 토큰 값
     * @param amount 결제 금액
     * @return 결제 상태
     */
    public String processPayment(String tokenValue, Double amount) {
        Payment payment = new Payment();
        payment.setTokenValue(tokenValue);
        payment.setAmount(amount);
        payment.setStatus("승인"); // 여기서는 기본적으로 승인 처리
        payment.setPaymentDt(LocalDateTime.now());

        paymentRepository.save(payment);

        return payment.getStatus();
    }
}
