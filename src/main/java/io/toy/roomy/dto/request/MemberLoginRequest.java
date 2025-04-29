package io.toy.roomy.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginRequest {
    private String username;
    private String password;
}
