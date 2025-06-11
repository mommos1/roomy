package io.toy.roomy.controller.user;

import io.toy.roomy.dto.request.room.RoomReserveRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final ReservationService reservationService;

    public ReserveController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * 객실 예약 등록
     * @param dto 객실 정보
     * @return 처리 결과
     */
    @PostMapping
    public ResponseEntity<?> regReservation(@RequestBody RoomReserveRequest dto) {
        reservationService.regReservation(dto);
        return ResponseEntity.ok(ApiResponse.success("객실 등록 성공"));
    }

}
