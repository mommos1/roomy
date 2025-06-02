package io.toy.roomy.service;

import io.toy.roomy.domain.Reservation;
import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.room.RoomReserveRequest;
import io.toy.roomy.repository.ReservationRepository;
import io.toy.roomy.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    @Override
    public void regReservation(RoomReserveRequest dto) {
        Room room = roomRepository.findById(dto.getRoomId()).orElseThrow();
        Reservation reservation = Reservation.builder()
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .reservationCount(dto.getReservationCount())
                .reserveName(dto.getReserveName())
                .room(room)
                .build();

        reservationRepository.save(reservation);
    }
}
