package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 유저
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return CommonUtil.commonModelLayout(model, "home");
    }
}
