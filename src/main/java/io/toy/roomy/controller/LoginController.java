package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    CommonUtil commonUtil = new CommonUtil();

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        return commonUtil.commonModelLayout(model, "login/signup");
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return commonUtil.commonModelLayout(model, "login/login");
    }

}
