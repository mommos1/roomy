package io.toy.roomy.controller.reservation;

import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.AdminStayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/stay")
public class AdminStayController {

    public final AdminStayService reservationService;

    public AdminStayController(AdminStayService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/regStay")
    public ResponseEntity<ApiResponse> regAccommodation(@RequestBody StayRequest dto) {
        reservationService.regStay(dto);
        return ResponseEntity.ok(ApiResponse.success("숙소 등록 성공"));
    }
}
