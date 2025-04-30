package io.toy.roomy.dto.response;

import io.toy.roomy.domain.type.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {
    private String username;
    private String name;
    private MemberType memberType;
}
