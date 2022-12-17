package com.example.marketour.services;

import com.example.marketour.repositories.TourRepository;
import org.springframework.stereotype.Service;


@Service
public class TourService {
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
}
