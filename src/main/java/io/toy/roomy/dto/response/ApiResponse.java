package io.toy.roomy.dto.response;

import lombok.Builder;

@Builder
public record ApiResponse(
        String result,    // "Y" or "N"
        String data
) {
    public static ApiResponse success(String data) {
        return ApiResponse.builder()
                .result("Y")
                .data(data)
                .build();
    }
}