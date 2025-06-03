package io.toy.roomy.service;

import io.toy.roomy.domain.Reservation;
import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.room.RoomReserveRequest;
import io.toy.roomy.repository.ReservationRepository;
import io.toy.roomy.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
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

    /**
     * 객실 예약 등록
     * @param dto 예약 요청 dto
     */
    @Transactional
    @Override
    public void regReservation(RoomReserveRequest dto) {
        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("해당 객실이 존재하지 않습니다"));

        //체크아웃 날짜가 체크인 날짜보다 빠르거나 같을 시
        if (dto.getCheckInDate().isAfter(dto.getCheckOutDate()) || dto.getCheckInDate().isEqual(dto.getCheckOutDate())) {
            throw new IllegalArgumentException("체크인 날짜는 체크아웃 날짜보다 이전이어야 합니다.");
        }

        //겹치는 예약 수 확인
        long overlappingCount = reservationRepository.countOverlappingReservations(
                room,
                dto.getCheckInDate(),
                dto.getCheckOutDate()
        );

        //예약된 개수가 최대 객실보다 같거나 넘을 시 예외
        if (overlappingCount >= 1) {
            throw new IllegalStateException(
                    String.format("선택하신 날짜에 해당 객실은 이미 예약 한도(%d개)를 초과했습니다. (현재 겹치는 예약: %d개)",
                            1, overlappingCount)
            );
        }

        //예약 등록
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
