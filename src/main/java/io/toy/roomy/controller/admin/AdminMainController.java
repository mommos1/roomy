package io.toy.roomy.controller.admin;

import io.toy.roomy.common.CommonUtil;
import io.toy.roomy.dto.response.room.RoomDetailRecord;
import io.toy.roomy.dto.response.stay.StayDetailRecord;
import io.toy.roomy.dto.response.room.AdminRoomListResponse;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.service.admin.AdminRoomService;
import io.toy.roomy.service.admin.AdminStayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 관리자페이지 이동 관련 Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminMainController {

    private final AdminStayService adminStayService;
    private final AdminRoomService adminRoomService;

    public AdminMainController(AdminStayService adminStayService, AdminRoomService adminRoomService) {
        this.adminStayService = adminStayService;
        this.adminRoomService = adminRoomService;
    }

    /**
     * 관리자페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("/main")
    public String adminHome(Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminHome");
    }

    /**
     * 숙소 관리 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("/stay/list")
    public String adminStayList(Model model) {
        List<StayListResponse> adminStayList = adminStayService.getAll();
        model.addAttribute("adminStayList", adminStayList);
        return CommonUtil.commonModelAdminLayout(model, "admin/adminStayList");
    }

    /**
     * 숙소 등록 페이지
     * @param model title, css, content
     * @return adminLayout
     */
    @GetMapping("/stay/regPage")
    public String adminStayRegPage(Model model) {
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRegStay");
    }

    /**
     * 숙소 수정 페이지
     * @param model title, css, content
     * @param stayId 숙소 id값
     * @return adminLayout
     */
    @GetMapping("/stay/updatePage")
    public String adminStayUpdatePage(
            @RequestParam("stayId") Long stayId,
            Model model) {
        StayDetailRecord stayDetail = adminStayService.getStayDetail(stayId);
        model.addAttribute("stay", stayDetail);
        model.addAttribute("stayId", stayId);

        return CommonUtil.commonModelAdminLayout(model, "admin/adminUpdateStay");
    }

    /**
     * 객실 관리 페이지
     * @param model title, css, content
     * @param stayId 숙소 id값
     * @return adminLayout
     */
    @GetMapping("/room/list")
    public String adminRoomList(@RequestParam("stayId") Long stayId, Model model) {
        List<AdminRoomListResponse> adminRoomList = adminRoomService.getRoomList(stayId);

        model.addAttribute("stayId", stayId);
        model.addAttribute("adminRoomList", adminRoomList);
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRoomList");
    }

    /**
     * 객실 등록 페이지
     * @param model title, css, content
     * @param stayId 숙소 id값
     * @return adminLayout
     */
    @GetMapping("/room/regPage")
    public String adminRoomRegPage(@RequestParam("stayId") Long stayId, Model model) {
        model.addAttribute("stayId", stayId);
        return CommonUtil.commonModelAdminLayout(model, "admin/adminRegRoom");
    }

    /**
     * 객실 수정 페이지
     * @param model title, css, content
     * @param roomId 객실 id값
     * @return adminLayout
     */
    @GetMapping("/room/updatePage")
    public String adminRoomUpdatePage(
            @RequestParam("stayId") Long stayId,
            @RequestParam("roomId") Long roomId,
            Model model) {
        RoomDetailRecord roomDetail = adminRoomService.getRoomDetail(roomId);
        model.addAttribute("room", roomDetail);
        model.addAttribute("roomId", roomId);
        model.addAttribute("stayId", stayId);

        return CommonUtil.commonModelAdminLayout(model, "admin/adminUpdateRoom");
    }
}
