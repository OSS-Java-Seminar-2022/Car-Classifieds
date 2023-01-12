package com.example.marketour.controllers;

import com.example.marketour.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class InitController {
    @GetMapping
    String init(Model model) {
        return "login";
    }

    @GetMapping("/updateLocation")
    String tryFunc(Model model, @RequestParam(name = "longitude") Double longitude, @RequestParam(name = "latitude") Double latitude) {
        Logger.getGlobal().log(Level.INFO, "longitude: " + longitude + "\n" + "latitude: " + latitude);
        return "login";
    }


}
