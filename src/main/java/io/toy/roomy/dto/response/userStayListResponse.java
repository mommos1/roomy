package io.toy.roomy.dto.response;

import io.toy.roomy.domain.Stay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class userStayListResponse {
    private Long id;
    private String name;
    private String type;
    private String filePath;

    public static userStayListResponse from(Stay stay) {
        return userStayListResponse.builder()
                .id(stay.getId())
                .name(stay.getName())
                .type(stay.getType().name())
                .filePath(stay.getFilePath())
                .build();
    }
}