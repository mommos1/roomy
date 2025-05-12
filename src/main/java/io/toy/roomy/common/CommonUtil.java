package io.toy.roomy.common;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

public class CommonUtil {

    /**
     * 사용자용 레이아웃
     * @param model layout 요소
     * @param page content 페이지
     * @return 사용자용 레이아웃
     */
    public static String commonModelLayout(Model model, String page) {
        model.addAttribute("title", page + " :: title");
        model.addAttribute("css", page + " :: css");
        model.addAttribute("content", page + " :: content");

        return "/layout/user/layout";
    }

    /**
     * 관리자용 레이아웃
     * @param model layout 요소
     * @param page content 페이지
     * @return 관리자용 레이아웃
     */
    public static String commonModelAdminLayout(Model model, String page) {
        model.addAttribute("title", page + " :: title");
        model.addAttribute("css", page + " :: css");
        model.addAttribute("content", page + " :: content");

        return "/layout/admin/layout";
    }
}
