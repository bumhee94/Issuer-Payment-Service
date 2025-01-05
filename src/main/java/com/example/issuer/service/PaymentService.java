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
    @Autowired
    private ExternalApiService externalApiService;


    /**
     * 결제 승인/거절 처리
     *
     * @param tokenValue 토큰 값
     * @param amount 결제 금액
     * @return 결제 상태
     */
    public String processPayment(String tokenValue, Double amount) {
        // 1. TSP에 토큰 검증 요청
        Boolean isValid = externalApiService.postToTspVerify(tokenValue);

        if (!isValid) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        // 2. 결제 처리
        Payment payment = new Payment();
        payment.setTokenValue(tokenValue);
        payment.setAmount(amount);
        payment.setStatus("승인"); // 기본적으로 승인 처리
        payment.setPaymentDt(LocalDateTime.now());

        paymentRepository.save(payment);

        return payment.getStatus();
    }
}
