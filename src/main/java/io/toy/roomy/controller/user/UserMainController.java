package io.toy.roomy.controller.user;

import io.toy.roomy.common.CommonUtil;
import io.toy.roomy.dto.response.room.RoomRecord;
import io.toy.roomy.dto.response.stay.StayDetailRecord;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.service.RoomService;
import io.toy.roomy.service.StayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserMainController {

    private final StayService stayService;
    private final RoomService roomSerervice;

    public UserMainController(StayService StayService, RoomService roomService) {
        this.stayService = StayService;
        this.roomSerervice = roomService;
    }

    /**
     * 호텔, 모텔 의 리스트를 반환합니다.
     * @return 호텔, 모텔 리스트
     */
    @GetMapping("/stay/list")
    public String getUserStayListPage(Model model) {
        List<StayListResponse> userStayList = stayService.getStayList();
        model.addAttribute("userStayList", userStayList);
        return CommonUtil.commonModelLayout(model, "user/stayList");
    }

    /**
     * 호텔, 모텔 의 상세 페이지를 반환합니다.
     * @return 호텔, 모텔 상세페이지
     */
    @GetMapping("/stay/info/{stayId}")
    public String getUserStayInfoPage(
            Model model,
            @PathVariable Long stayId) {

        StayDetailRecord stayDetail = stayService.getStayDetail(stayId);
        List<RoomRecord> roomDetail = stayService.getRoomsByStayID(stayId);

        model.addAttribute("stayDetail", stayDetail);
        model.addAttribute("roomDetail", roomDetail);

        return CommonUtil.commonModelLayout(model, "user/stayInfo");
    }

    @GetMapping("/reserve/room/{roomId}")
    public String getUserStayReserveRoomPage(
            Model model,
            @PathVariable Long roomId) {

        RoomRecord room = roomSerervice.getRoomDetail(roomId);

        model.addAttribute("room", room);

        return CommonUtil.commonModelLayout(model, "user/reserveRoom");
    }
}
