package io.toy.roomy.controller.reservation;

import io.toy.roomy.dto.request.AccommodationRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/accommodation")
public class AccommodationController {

    public final ReservationService reservationService;

    public AccommodationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/regAccommodation")
    public ResponseEntity<ApiResponse> regAccommodation(@RequestBody AccommodationRequest dto) {
        reservationService.regAccommodation(dto);
        return ResponseEntity.ok(ApiResponse.success("로그인 성공"));
    }
}
