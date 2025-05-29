package io.toy.roomy.dto.request;

import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 요청 시 사용
 */
@Getter
@Builder
public class MemberLoginRequest {
    private String username;
    private String password;
}
