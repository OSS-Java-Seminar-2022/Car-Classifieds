package com.example.marketour.services;

import com.example.marketour.model.entities.Tour;
import com.example.marketour.model.entities.TourReview;
import com.example.marketour.model.entities.User;
import com.example.marketour.repositories.TourReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TourReviewService {
    private final TourReviewRepository tourReviewRepository;

    public TourReviewService(TourReviewRepository tourReviewRepository) {
        this.tourReviewRepository = tourReviewRepository;
    }

    public List<TourReview> getAllReviewsOfTour(Tour tour) {
        return tourReviewRepository.findAll().stream().filter(e -> Objects.equals(e.getTour().getTourId(), tour.getTourId())).collect(Collectors.toList());
    }

    public Double calculateRateForTour(Tour tour) {
        final var allReviews = getAllReviewsOfTour(tour);
        return allReviews.stream().map(TourReview::getRate).reduce(Long::sum).get().doubleValue() / allReviews.size();
    }

    public void addNewReview(User user, String text, Long rate, Tour tour) {
        final var time = System.currentTimeMillis();
        final var newTourReview = new TourReview();
        newTourReview.setTour(tour);
        newTourReview.setUser(user);
        newTourReview.setTime(time);
        newTourReview.setRate(rate);
        newTourReview.setText(text);
        tourReviewRepository.save(newTourReview);
    }
}
