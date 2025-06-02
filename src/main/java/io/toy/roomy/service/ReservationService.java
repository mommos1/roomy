package io.toy.roomy.service;

import io.toy.roomy.domain.Reservation;
import io.toy.roomy.dto.request.room.RoomReserveRequest;

public interface ReservationService {
    void regReservation(RoomReserveRequest dto);
}
