package io.toy.roomy.dto.request;

import lombok.Getter;

@Getter
public class RoomRequest {
    private String name;
    private int pricePerNight;
    private int capacity;
    private String description;
}