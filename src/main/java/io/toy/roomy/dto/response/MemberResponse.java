package io.toy.roomy.dto.response;

import io.toy.roomy.domain.type.MemberType;
import lombok.Builder;
import lombok.Getter;

//삭제예정 ( 250501 JWT 추가로 반환 객체 변경)
@Getter
@Builder
public class MemberResponse {
    private String username;
    private String name;
    private MemberType memberType;
}
