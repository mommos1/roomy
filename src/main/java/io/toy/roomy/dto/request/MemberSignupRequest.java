package io.toy.roomy.dto.request;

import io.toy.roomy.domain.type.MemberType;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 요청 시 사용
 */
@Getter
@Builder
public class MemberSignupRequest {
    private String username;
    private String password;
    private String name;
    private MemberType memberType;
}