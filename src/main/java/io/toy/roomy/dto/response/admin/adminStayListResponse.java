package io.toy.roomy.dto.response.admin;

import io.toy.roomy.domain.Stay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class adminStayListResponse {
    private Long id;
    private String name;
    private String location;
    private String type;
    private LocalDateTime regDt;

    public static adminStayListResponse from(Stay stay) {
        return adminStayListResponse.builder()
                .id(stay.getId())
                .name(stay.getName())
                .location(stay.getLocation())
                .type(stay.getType().name())
                .regDt(stay.getRegDt())
                .build();
    }
}
