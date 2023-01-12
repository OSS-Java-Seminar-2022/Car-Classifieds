package com.example.marketour.controllers;

import com.example.marketour.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String indexPage() {
        return "home";
    }
    @GetMapping(value = "/login")
    String login(@ModelAttribute("user") User user) {
        return "login";
    }

}