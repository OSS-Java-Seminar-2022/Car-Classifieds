package com.example.marketour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Spring Boot x Tailwind CSS");

        return "login";
    }

}