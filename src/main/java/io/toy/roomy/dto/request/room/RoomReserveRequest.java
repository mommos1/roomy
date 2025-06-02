package io.toy.roomy.dto.request.room;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RoomReserveRequest {
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int reservationCount;
    private String reserveName;
}
