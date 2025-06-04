package io.toy.roomy.dto.response.room;

import lombok.Builder;

/**
 * 객실 정보 조회 시 사용
 */
@Builder
public record RoomRecord(
    Long id,
    String name,
    int numberOfRooms,
    int pricePerNight,
    int capacity,
    String description
) {}
