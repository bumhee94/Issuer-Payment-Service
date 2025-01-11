package com.example.issuer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenValue; // TSP에서 받은 토큰 값

    @Column(nullable = false)
    private Double amount; // 결제 금액

    @Column(nullable = false)
    private String status; // 결제 상태 (승인/거절)

    @Column(nullable = false)
    private LocalDateTime paymentDt; // 결제 시간
}
