package io.toy.roomy.dto.response.room;

import io.toy.roomy.domain.Room;
import lombok.Builder;
import lombok.Getter;

/**
 * 관리자페이지 내 객실 목록 조회 시 사용
 */
@Builder
@Getter
public class adminRoomListResponse {
    private Long id;
    private String name;
    private int pricePerNight;
    private int capacity;
    private String description;

    public static adminRoomListResponse from(Room room) {
        return adminRoomListResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .pricePerNight(room.getPricePerNight())
                .capacity(room.getCapacity())
                .description(room.getDescription())
                .build();
    }
}
