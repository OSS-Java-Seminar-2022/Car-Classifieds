package com.example.marketour.controllers;

import com.example.marketour.repositories.TourReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class TourReviewController {

    final TourReviewRepository tourReviewRepository;

    public TourReviewController(TourReviewRepository tourReviewRepository) {
        this.tourReviewRepository = tourReviewRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllReviewsForTour(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }
        final var tourId = (Long) model.getAttribute("tourId");
        return ResponseEntity.ok(tourReviewRepository.findAll().stream().filter(e -> e.getTour().getTourId().equals(tourId)).collect(Collectors.toList()));
    }
}
