package com.example.marketour.services;

import com.example.marketour.model.entities.*;
import com.example.marketour.repositories.GuideTourRepository;
import com.example.marketour.repositories.TouristTourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TourService {
    private final TouristTourRepository touristTourRepository;
    private final GuideTourRepository guideTourRepository;

    public TourService(TouristTourRepository touristTourRepository, GuideTourRepository guideTourRepository) {
        this.touristTourRepository = touristTourRepository;
        this.guideTourRepository = guideTourRepository;
    }

    public List<TouristTour> getAllTouristsTours(User user) {
        return touristTourRepository.findAll().stream().filter(tour -> tour.getTourist().sameUser(user) && user.getUserType() == UserType.tourist).collect(Collectors.toList());
    }

    public List<GuideTour> getAllGuideTours(User user) {
        return guideTourRepository.findAll().stream().filter(tour -> tour.getGuide().sameUser(user) && user.getUserType() == UserType.guide).collect(Collectors.toList());
    }

    public List<Tour> getAllToursOnMarketplace() {
        return guideTourRepository.findAll().stream().map(GuideTour::getTour).filter(Tour::isVisibleOnMarket).collect(Collectors.toList());
    }

}
