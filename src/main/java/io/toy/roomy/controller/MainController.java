package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return CommonUtil.commonModelLayout(model, "home");
    }
}
