package com.example.marketour.controllers;

import com.example.marketour.model.entities.City;
import com.example.marketour.model.entities.Country;
import com.example.marketour.model.entities.User;
import com.example.marketour.model.entities.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RouteController {

    @GetMapping("/")
    public String indexPage() {
        return "home";
    }

    @GetMapping(value = "/login")
    String login(@ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping(value = "/register")
    String register(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("cities", City.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("userTypes", UserType.values());
        return "register";
    }

    @GetMapping(value = "/main")
    String main(@ModelAttribute("user") User user, Model model) {
        return "main";
    }

}