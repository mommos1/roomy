package io.toy.roomy.repository;

import io.toy.roomy.domain.Reservation;
import io.toy.roomy.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * 특정 객실에 대해 주어진 기간과 겹치는 기존 예약의 개수를 반환합니다.
     * 겹치는 조건: (새로운 체크인 날짜 < 기존 예약의 체크아웃 날짜) AND (새로운 체크아웃 날짜 > 기존 예약의 체크인 날짜)
     * @param room 확인할 객실
     * @param newCheckInDate 새로운 예약의 체크인 날짜
     * @param newCheckOutDate 새로운 예약의 체크아웃 날짜
     * @return 겹치는 예약의 개수
     */
    @Query("SELECT COUNT(r) FROM Reservation r " +
            "WHERE r.room = :room " +
            "AND r.checkInDate < :newCheckOutDate " +
            "AND r.checkOutDate > :newCheckInDate")
    long countOverlappingReservations(@Param("room") Room room,
                                      @Param("newCheckInDate") LocalDate newCheckInDate,
                                      @Param("newCheckOutDate") LocalDate newCheckOutDate);
}
