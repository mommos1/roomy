package io.toy.roomy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "home :: title");
        model.addAttribute("css", "home :: css");
        model.addAttribute("content", "home :: content");

        return "/layout/layout";
    }

    @GetMapping("/selectSpace")
    public String getSelectSpacePage(Model model) {
        model.addAttribute("title", "selectSpace :: title");
        model.addAttribute("css", "selectSpace :: css");
        model.addAttribute("content", "selectSpace :: content");

        return "/layout/layout";
    }
}
