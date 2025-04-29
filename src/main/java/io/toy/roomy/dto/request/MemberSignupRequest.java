package io.toy.roomy.dto.request;

import io.toy.roomy.domain.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MemberSignupRequest {
    private String username;
    private String password;
    private String name;
    private MemberType memberType;
}