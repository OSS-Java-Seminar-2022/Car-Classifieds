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
import java.util.HashMap;
import java.util.Map;
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
        final var result = tourController.getAllToursOfThisUser(httpServletRequest, model);
        if (result.getBody() instanceof ArrayList) {
            Map<Long, Image> tourIdToImageMap = new HashMap<>();
            if (!((ArrayList<?>) result.getBody()).isEmpty() && ((ArrayList<?>) result.getBody()).get(0) instanceof TouristTour) {
                tourIdToImageMap = ((ArrayList<TouristTour>) result.getBody()).stream().map(touristTour -> Map.entry(touristTour.getTour().getTourId(), imageController.getTourImages(touristTour.getTour().getTourId()).getBody().get(0))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            } else if (!((ArrayList<?>) result.getBody()).isEmpty() && ((ArrayList<?>) result.getBody()).get(0) instanceof GuideTour) {
                tourIdToImageMap = ((ArrayList<GuideTour>) result.getBody()).stream().map(guideTour -> Map.entry(guideTour.getTour().getTourId(), imageController.getTourImages(guideTour.getTour().getTourId()).getBody().get(0))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }
            final var finalMap = tourIdToImageMap.entrySet().stream().map(entry -> Map.entry(entry.getKey(), Base64.getEncoder().encodeToString(entry.getValue().getData()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            model.addAttribute("userTours", result.getBody());
            model.addAttribute("imageMap", finalMap);
        } else {
            //TODO Error handling
        }


        return "main";
    }

}