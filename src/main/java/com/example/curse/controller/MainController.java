package com.example.curse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "welcome";
    }

    @GetMapping("/schedule")
    public String scheduleRedirect() {
        return "redirect:/schedule/view"; // Перенаправляем на основную страницу расписания
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}


