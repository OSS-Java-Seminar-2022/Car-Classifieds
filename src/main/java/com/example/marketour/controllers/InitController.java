package com.example.marketour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    @GetMapping
    String init(Model model) {
        return "login";
    }

    @GetMapping(value = "/validLogin")
    String validLogin() {
        return "tourist_main_screen";
    }
}
