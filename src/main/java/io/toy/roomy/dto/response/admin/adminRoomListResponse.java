package io.toy.roomy.dto.response.admin;

import io.toy.roomy.domain.Stay;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class adminRoomListResponse {
    private Long id;
    private String name;
    private

    public static adminRoomListResponse from(Stay stay) {
        return adminRoomListResponse.builder()
                .id(stay.getId())
                .name(stay.getName())
                .type(stay.getType().name())
                .regDt(stay.getRegDt())
                .build();
    }
}
