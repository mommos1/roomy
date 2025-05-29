package io.toy.roomy.controller.user;

import io.toy.roomy.common.CommonUtil;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.service.user.UserStayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stay")
public class UserMainController {

    private final UserStayService userStayService;

    public UserMainController(UserStayService userStayService) {
        this.userStayService = userStayService;
    }

    /**
     * 호텔, 모텔 의 리스트를 반환합니다.
     * @return 호텔, 모텔 리스트
     */
    @GetMapping("/list")
    public String getUserStayListPage(Model model) {
        List<StayListResponse> userStayList = userStayService.getStayListAll();
        model.addAttribute("userStayList", userStayList);
        return CommonUtil.commonModelLayout(model, "reservation/stayList");
    }

    /**
     * 호텔, 모텔 의 상세 페이지를 반환합니다.
     * @return 호텔, 모텔 상세페이지
     */
    @GetMapping("/info/{stayId}")
    public String getUserStayInfoPage(Model model,
              @PathVariable Long stayId) {

        return CommonUtil.commonModelLayout(model, "reservation/stayInfo");
    }
}
