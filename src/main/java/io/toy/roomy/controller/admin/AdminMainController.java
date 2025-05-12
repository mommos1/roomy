package io.toy.roomy.controller.admin;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {

    /**
     * 관리자페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("/admin/main")
    public String adminHome(Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminHome");
    }

    /**
     * 숙소 관리 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("admin/stay/list")
    public String adminStayList(Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminStayList");
    }

    /**
     * 숙소 등록 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("admin/stay/regPage")
    public String adminStayRegPage(Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRegStay");
    }
}
