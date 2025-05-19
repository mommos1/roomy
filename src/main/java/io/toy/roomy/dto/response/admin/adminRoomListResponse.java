package io.toy.roomy.dto.response.admin;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import lombok.Builder;
import lombok.Getter;

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
