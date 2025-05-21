package io.toy.roomy.dto.response.admin;

import io.toy.roomy.domain.Room;
import lombok.Getter;

@Getter
public class RoomDetailResponse {
    private Long id;
    private String name;
    private int pricePerNight;
    private int capacity;
    private String description;

    public static RoomDetailResponse from(Room room) {
        RoomDetailResponse dto = new RoomDetailResponse();
        dto.id = room.getId();
        dto.name = room.getName();
        dto.capacity = room.getCapacity();
        dto.pricePerNight = room.getPricePerNight();
        dto.description = room.getDescription();
        return dto;
    }
}
