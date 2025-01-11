package com.example.issuer.controller;

import com.example.issuer.dto.PaymentRequest;
import com.example.issuer.dto.PaymentResponse;
import com.example.issuer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuer")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 결제 요청 API
     *
     * @return 결제 상태
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}
