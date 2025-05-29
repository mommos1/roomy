package io.toy.roomy.dto.response;

import lombok.Builder;

@Builder
public class MemberResponse {
    private String username;
    private String password;
}
