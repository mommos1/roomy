package io.toy.roomy.controller.admin;

import io.toy.roomy.common.CommonUtil;
import io.toy.roomy.dto.response.adminStayListResponse;
import io.toy.roomy.service.AdminStayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 관리자페이지 이동
 */
@Controller
public class AdminMainController {

    private final AdminStayService adminStayService;

    public AdminMainController(AdminStayService adminStayService) {
        this.adminStayService = adminStayService;
    }

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
        List<adminStayListResponse> adminStayList = adminStayService.getAll();
        model.addAttribute("adminStayList", adminStayList);
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

    /**
     * 객실 관리 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("admin/room/list")
    public String adminRoomList(Model model) {
        List<adminStayListResponse> adminStayList = adminStayService.getAll();
        model.addAttribute("adminStayList", adminStayList);
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRoomList");
    }

    /**
     * 객실 등록 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("admin/room/regPage")
    public String adminRoomRegPage(@RequestParam("id") String id, Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRegRoom");
    }
}
