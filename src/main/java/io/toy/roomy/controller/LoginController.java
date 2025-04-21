package io.toy.roomy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/signup")
    public String getSignupPage() {
        return "/login/signup";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        model.addAttribute("title", "login/login :: title");
        model.addAttribute("css", "login/login :: css");
        model.addAttribute("content", "login/login :: content");

        return "layout/layout";
    }

}
