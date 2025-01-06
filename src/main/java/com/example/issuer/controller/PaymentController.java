package com.example.issuer.controller;

import com.example.issuer.dto.PaymentRequest;
import com.example.issuer.dto.PaymentResponse;
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
     * @return 결제 상태
     */
    @PostMapping("/process-payment")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestBody PaymentRequest request) {
        String status = paymentService.processPayment(request.getTokenValue(), request.getAmount());
        PaymentResponse res = new PaymentResponse();
        res.setMessage(status);
        res.setSuccess(true);
        return ResponseEntity.ok(res);
    }
}
