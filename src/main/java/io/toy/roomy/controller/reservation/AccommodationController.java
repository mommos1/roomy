package io.toy.roomy.controller.reservation;

import io.toy.roomy.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/accommodation")
public class AccommodationController {

    public final ReservationService reservationService;

    public AccommodationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/")
}
