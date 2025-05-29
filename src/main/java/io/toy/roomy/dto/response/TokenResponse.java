package io.toy.roomy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JWT 토큰 응답 시 사용
 */
@Getter
@AllArgsConstructor
public class TokenResponse {
    private String token;
}

