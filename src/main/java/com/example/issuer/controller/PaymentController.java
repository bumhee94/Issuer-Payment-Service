package com.example.issuer.controller;

import com.example.issuer.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issuer")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 결제 요청 API
     *
     * @param tokenValue 토큰 값
     * @param amount 결제 금액
     * @return 결제 상태
     */
    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(
            @RequestParam String tokenValue,
            @RequestParam Double amount) {
        String status = paymentService.processPayment(tokenValue, amount);
        return ResponseEntity.ok(status);
    }
}
