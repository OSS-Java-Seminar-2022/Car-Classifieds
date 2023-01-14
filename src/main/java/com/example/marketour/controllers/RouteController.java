package com.example.marketour.controllers;

import com.example.marketour.model.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class RouteController {
    private final TourController tourController;
    private final ImageController imageController;

    public RouteController(TourController tourController, ImageController imageController) {
        this.tourController = tourController;
        this.imageController = imageController;
    }

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
    String main(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest, Model model) {
        final var userSpecificTours = tourController.getAllToursOfThisUser(httpServletRequest, model);
        final var allTours = tourController.getAllToursOnMarket(httpServletRequest, model);
        final var imageMap = ((ArrayList<Tour>) allTours.getBody()).stream().map(tour -> Map.entry(tour.getTourId(), Objects.requireNonNull(Base64.getEncoder().encodeToString(Objects.requireNonNull(imageController.getFirstPageImage(tour.getTourId()).getBody()).getData())))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        model.addAttribute("userTours", userSpecificTours.getBody());
        model.addAttribute("allTours", allTours.getBody());
        model.addAttribute("imageMap", imageMap);
        return "main";
    }

}