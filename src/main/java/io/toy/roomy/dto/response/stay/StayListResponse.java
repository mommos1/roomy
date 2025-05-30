package io.toy.roomy.dto.response.stay;

import io.toy.roomy.domain.Stay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 관리자 페이지 내 숙소 조회 시 사용
 */
@Getter
@Builder
public class StayListResponse {
    private Long id;
    private String name;
    private String location;
    private String type;
    private LocalDateTime regDt;
    private String filePath;

    public static StayListResponse from(Stay stay) {
        return StayListResponse.builder()
                .id(stay.getId())
                .name(stay.getName())
                .location(stay.getLocation())
                .type(stay.getType().name())
                .regDt(stay.getRegDt())
                .filePath(stay.getFilePath())
                .build();
    }
}
