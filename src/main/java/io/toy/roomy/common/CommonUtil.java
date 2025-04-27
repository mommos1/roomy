package io.toy.roomy.common;

import org.springframework.ui.Model;

public class CommonUtil {

    public String commonModelLayout(Model model, String page) {
        model.addAttribute("title", page + " :: title");
        model.addAttribute("css", page + " :: css");
        model.addAttribute("content", page + " :: content");

        return "/layout/layout";
    }
}
