package io.toy.roomy.controller.reservation;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stay")
public class UserStayController {

    /**
     * 호텔, 모텔 의 리스트를 반환합니다.
     * @return 호텔, 모텔 리스트
     */
    @GetMapping("/list")
    public String getUserStayListPage(Model model) {
        return CommonUtil.commonModelLayout(model, "reservation/user/stayList");
    }

    /**
     * 호텔, 모텔 의 상세 페이지를 반환합니다.
     * @return 호텔, 모텔 상세페이지
     */
    @GetMapping("/info")
    public String getUserStayInfoPage(Model model) {
        return CommonUtil.commonModelLayout(model, "reservation/user/stayInfo");
    }
}
