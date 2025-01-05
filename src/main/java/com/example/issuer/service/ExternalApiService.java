package com.example.issuer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ExternalApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TSP_URL = "http://localhost:8081/api/tsp/verify-token";

    /**
     * TSP로 토큰 검증 요청을 보내고 응답받기
     *
     * @param tokenValue 검증할 토큰 값
     * @return TSP로부터의 검증 결과 (true/false)
     */
    public Boolean postToTspVerify(String tokenValue) {
        log.info("TSP로 토큰 검증 요청 시작: TokenValue={}", tokenValue);

        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                    TSP_URL,
                    tokenValue,
                    Boolean.class
            );
            log.info("TSP로부터 토큰 검증 응답 수신: StatusCode={}, ResponseBody={}",
                    response.getStatusCode(), response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("TSP 토큰 검증 요청 실패: Message={}", e.getMessage(), e);
            throw new RuntimeException("TSP와 통신 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
