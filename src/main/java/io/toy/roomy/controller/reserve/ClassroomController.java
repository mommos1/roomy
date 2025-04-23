package io.toy.roomy.controller.reserve;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassroomController {

    @GetMapping("/reserve/classroom")
    public String getClassroomPage(Model model) {
        model.addAttribute("title", "reserve/classroom/reservation :: title");
        model.addAttribute("css", "reserve/classroom/reservation :: css");
        model.addAttribute("content", "reserve/classroom/reservation :: content");

        //return "/layout/layout";
        return "/reserve/classroom/reservation";
    }
}
