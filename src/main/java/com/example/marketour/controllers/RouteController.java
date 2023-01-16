package com.example.marketour.controllers;

import com.example.marketour.model.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

    private final UserController userController;

    public RouteController(TourController tourController, ImageController imageController, UserController userController) {
        this.tourController = tourController;
        this.imageController = imageController;
        this.userController = userController;
    }

    @GetMapping("/")
    public String indexPage() {
        return "home";
    }

    @GetMapping(value = "/login")
    String login(@ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping(value = "/pageCreate")
    String pageCreate(Model model, HttpServletRequest httpServletRequest) {
        return "pageCreate";
    }

    @GetMapping(value = "/newTour")
    String newTour(Model model, HttpServletRequest httpServletRequest) {
        final var user = (User) httpServletRequest.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "newTour";
    }

    @GetMapping(value = "/register")
    String register(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("cities", City.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("userTypes", UserType.values());
        return "register";
    }

    @GetMapping(value = "/main")
    String main(HttpServletRequest httpServletRequest, Model model) throws IOException {
        final var userSpecificTours = tourController.getAllToursOfThisUser(httpServletRequest, model);
        final var allTours = tourController.getAllToursOnMarket(httpServletRequest, model);
        final var imageMap = ((ArrayList<Tour>) allTours.getBody()).stream().map(tour -> Map.entry(tour.getTourId(), Objects.requireNonNull(Base64.getEncoder().encodeToString(Objects.requireNonNull(imageController.getFirstPageImage(tour.getTourId()).getBody()).getData())))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        final var user = (User) httpServletRequest.getSession().getAttribute("user");
        model.addAttribute("userTours", userSpecificTours.getBody());
        model.addAttribute("allTours", ((ArrayList<Tour>) allTours.getBody()).stream().filter(tour -> !((ArrayList<Tour>) userSpecificTours.getBody()).stream().map(tour1 -> tour1.getTourId()).collect(Collectors.toList()).contains(tour.getTourId())).collect(Collectors.toList()));
        model.addAttribute("imageMap", imageMap);
        if (user.getImage() != null) {
            model.addAttribute("userAvatar", Base64.getEncoder().encodeToString(user.getImage().getData()));
        } else {
            File newFile = new File("src/main/resources/static/img/covo.png");
            BufferedImage originalImage = ImageIO.read(newFile);
            ByteArrayOutputStream oStream = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", oStream);
            byte[] imageInByte = oStream.toByteArray();
            model.addAttribute("userAvatar", Base64.getEncoder().encodeToString(imageInByte));

        }
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping(value = "/logout")
    String logout(HttpServletRequest request) {
        final var response = userController.logout(request);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/";
        } else {
            //TODO error handling
            return "redirect:/";
        }
    }

}