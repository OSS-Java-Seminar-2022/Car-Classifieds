package com.example.marketour.controllers;

import com.example.marketour.model.entities.Filter;
import com.example.marketour.model.entities.User;
import com.example.marketour.model.entities.UserType;
import com.example.marketour.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/ofThisUser")
    public ResponseEntity<Object> getAllToursOfThisUser(HttpServletRequest request, Model model) {
        final var session = request.getSession();
        if (session != null) {
            final var user = (User) session.getAttribute("user");
            if (user != null) {
                final var filter = (Filter) model.getAttribute("filter");
                if (user.getUserType() == UserType.tourist) {
                    final var result = tourService.getAllTouristsTours(user, filter);
                    if (result != null) {
                        return ResponseEntity.ok(result);
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
                    }
                } else {
                    final var result = tourService.getAllGuideTours(user, filter);
                    if (result != null) {
                        return ResponseEntity.ok(result);
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

    }

    @GetMapping("/market")
    public ResponseEntity<Object> getAllToursOnMarket(HttpServletRequest request, Model model) {
        final var session = request.getSession();
        if (session.getAttribute("user") != null) {
            final var filter = (Filter) model.getAttribute("filter");
            final var result = tourService.getAllToursOnMarketplace(filter);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

    }
}
