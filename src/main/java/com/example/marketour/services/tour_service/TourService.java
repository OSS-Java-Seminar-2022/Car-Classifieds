package com.example.marketour.services.tour_service;

import com.example.marketour.repositories.tour_repository.TourRepository;
import org.springframework.stereotype.Service;


@Service
public class TourService {
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
}
