package io.toy.roomy.controller;

import io.toy.roomy.common.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 회원가입, 로그인 Controller
 */
@Controller
public class LoginController {

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        return CommonUtil.commonModelLayout(model, "login/signup");
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return CommonUtil.commonModelLayout(model, "login/login");
    }

}
