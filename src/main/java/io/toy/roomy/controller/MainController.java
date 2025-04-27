package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    CommonUtil commonUtil = new CommonUtil();

    @GetMapping("/")
    public String home(Model model) {

        return commonUtil.commonModelLayout(model, "home");
    }

    @GetMapping("/selectSpace")
    public String getSelectSpacePage(Model model) {

        return commonUtil.commonModelLayout(model, "selectSpace");
    }
}
