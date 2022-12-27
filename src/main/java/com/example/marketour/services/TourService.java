package com.example.marketour.services;

import com.example.marketour.model.entities.*;
import com.example.marketour.repositories.GuideTourRepository;
import com.example.marketour.repositories.TourRepository;
import com.example.marketour.repositories.TouristTourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.marketour.model.entities.Tour.filter;


@Service
public class TourService {
    private final TouristTourRepository touristTourRepository;
    private final GuideTourRepository guideTourRepository;
    private final TourRepository tourRepository;

    public TourService(TouristTourRepository touristTourRepository, GuideTourRepository guideTourRepository,
                       TourRepository tourRepository) {
        this.touristTourRepository = touristTourRepository;
        this.guideTourRepository = guideTourRepository;
        this.tourRepository = tourRepository;
    }

    public List<TouristTour> getAllTouristsTours(User user, Filter filter) {
        return touristTourRepository.findAll().stream().filter(tour -> tour.getTourist().sameUser(user) && user.getUserType() == UserType.tourist && filter(tour.getTour(), filter)).collect(Collectors.toList());
    }

    public List<GuideTour> getAllGuideTours(User user, Filter filter) {
        return guideTourRepository.findAll().stream().filter(tour -> tour.getGuide().sameUser(user) && user.getUserType() == UserType.guide && filter(tour.getTour(), filter)).collect(Collectors.toList());
    }

    public List<Tour> getAllToursOnMarketplace(Filter filter) {
        return guideTourRepository.findAll().stream().map(GuideTour::getTour).filter(e -> e.isVisibleOnMarket() && filter(e, filter)).collect(Collectors.toList());
    }

    public Tour findById(Long tourId) {
        return tourRepository.findAll().stream().filter(tour -> tour.getTourId().equals(tourId)).findFirst().orElse(null);
    }

}
