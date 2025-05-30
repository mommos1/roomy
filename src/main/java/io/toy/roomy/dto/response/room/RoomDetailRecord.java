package io.toy.roomy.dto.response.room;

import io.toy.roomy.domain.Room;

/**
 * 객실 정보 조회 시 사용
 */
public record RoomDetailRecord(
    Long id,
    String name,
    int pricePerNight,
    int capacity,
    String description
) {
    public static RoomDetailRecord from(Room room) {
        return new RoomDetailRecord(
                room.getId(),
                room.getName(),
                room.getPricePerNight(),
                room.getCapacity(),
                room.getDescription()
        );
    }
}
