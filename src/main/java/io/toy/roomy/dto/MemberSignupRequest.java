package io.toy.roomy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequest {
    private String username;
    private String password;
    private String name;
}