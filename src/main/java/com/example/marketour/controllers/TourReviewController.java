package com.example.marketour.controllers;

import com.example.marketour.model.entities.TourReview;
import com.example.marketour.repositories.TourReviewRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class TourReviewController {

    final TourReviewRepository tourReviewRepository;

    public TourReviewController(TourReviewRepository tourReviewRepository) {
        this.tourReviewRepository = tourReviewRepository;
    }

    @GetMapping
    public List<TourReview> getAllReviewsForTour(Model model) {
        final var tourId = (Long) model.getAttribute("tourId");
        return tourReviewRepository.findAll().stream().filter(e -> e.getTour().getTourId().equals(tourId)).collect(Collectors.toList());
    }
}
