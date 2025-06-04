package io.toy.roomy.dto.request.room;

import lombok.Builder;
import lombok.Getter;

/**
 * 객실 등록, 수정 시 사용
 */
@Getter
@Builder
public class RoomRequest {
    private Long id;
    private String name;
    private int numberOfRooms;
    private int pricePerNight;
    private int capacity;
    private String description;
    private Long stayId;
}