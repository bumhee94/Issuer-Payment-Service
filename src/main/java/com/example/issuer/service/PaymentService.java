package com.example.issuer.service;

import com.example.issuer.dto.PaymentRequest;
import com.example.issuer.dto.PaymentResponse;
import com.example.issuer.entity.Payment;
import com.example.issuer.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ExternalApiService externalApiService;


    /**
     * 결제 승인/거절 처리
     *
     * @param paymentRequest 토큰 값
     * @param paymentRequest 결제 금액
     * @return 결제 상태
     */
    @Transactional
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // 1. TSP에 토큰 검증 요청
        Boolean isValid = externalApiService.postToTspVerify(paymentRequest.getTokenValue());

        if (!isValid) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        // 2. 결제 처리
        Payment payment = Payment.builder()
                .tokenValue(paymentRequest.getTokenValue())
                .amount(paymentRequest.getAmount())
                .status("결제 승인") // 기본적으로 승인 처리
                .paymentDt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        return new PaymentResponse(true, "결제가 성공적으로 처리되었습니다.");
    }
}
