package io.toy.roomy.dto.request.room;

import lombok.Getter;

@Getter
public class RoomRequest {
    private String name;
    private int pricePerNight;
    private int capacity;
    private String description;
    private Long stayId;
}