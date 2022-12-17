package com.example.marketour.services;

import com.example.marketour.model.entities.GuideTour;
import com.example.marketour.model.entities.Tour;
import com.example.marketour.model.entities.TouristTour;
import com.example.marketour.model.entities.User;
import com.example.marketour.repositories.GuideTourRepository;
import com.example.marketour.repositories.TourRepository;
import com.example.marketour.repositories.TouristTourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TourService {
    private final TourRepository tourRepository;
    private final TouristTourRepository touristTourRepository;
    private final GuideTourRepository guideTourRepository;

    public TourService(TourRepository tourRepository, TouristTourRepository touristTourRepository, GuideTourRepository guideTourRepository) {
        this.tourRepository = tourRepository;
        this.touristTourRepository = touristTourRepository;
        this.guideTourRepository = guideTourRepository;
    }

    List<TouristTour> getAllTouristsTours(User user) {
        return touristTourRepository.findAll().stream().filter(tour -> tour.getTourist().sameUser(user)).collect(Collectors.toList());
    }

    List<Tour> getAllToursOnMarketplace() {
        return guideTourRepository.findAll().stream().map(GuideTour::getTour).filter(Tour::isVisibleOnMarket).collect(Collectors.toList());
    }

}
