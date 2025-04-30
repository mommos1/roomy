package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import io.toy.roomy.dto.request.AccommodationRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassroomController {

    CommonUtil commonUtil = new CommonUtil();
    private ReservationService reserveService;

    public ClassroomController(ReservationService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping("/reserve/classroom")
    public String getClassroomPage(Model model) {
        model.addAttribute("title", "reserve/classroom/reservation :: title");
        model.addAttribute("css", "reserve/classroom/reservation :: css");
        model.addAttribute("content", "reserve/classroom/reservation :: content");

        return "/reserve/classroom/reservation";
        //return commonUtil.commonModelLayout(model, "login/signup");
    }

    @PostMapping("reserve/api/test")
    public ResponseEntity<ApiResponse> test(AccommodationRequest dto) {
        reserveService.registerRoom(dto);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공"));
    }
}
