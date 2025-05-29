package io.toy.roomy.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 전역 예외 처리 시 사용
 */
@Getter
@Builder
public class ErrorResponse {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}

