package com.example.marketour.services;

import com.example.marketour.model.entities.*;
import com.example.marketour.repositories.GuideTourRepository;
import com.example.marketour.repositories.TourRepository;
import com.example.marketour.repositories.TouristTourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.marketour.model.entities.Tour.filter;


@Service
public class TourService {
    private final TouristTourRepository touristTourRepository;
    private final GuideTourRepository guideTourRepository;
    private final TourRepository tourRepository;

    private final LocationService locationService;

    public TourService(TouristTourRepository touristTourRepository, GuideTourRepository guideTourRepository,
                       TourRepository tourRepository, LocationService locationService) {
        this.touristTourRepository = touristTourRepository;
        this.guideTourRepository = guideTourRepository;
        this.tourRepository = tourRepository;
        this.locationService = locationService;
    }

    public List<Tour> getAllTouristsTours(User user, Filter filter) {
        return touristTourRepository.findAll().stream().filter(tour -> tour.getTourist().sameUser(user) && user.getUserType() == UserType.tourist && filter(tour.getTour(), filter)).map(TouristTour::getTour).collect(Collectors.toList());
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> getAllGuideTours(User user, Filter filter) {
        return guideTourRepository.findAll().stream().filter(tour -> tour.getGuide().sameUser(user) && user.getUserType() == UserType.guide && filter(tour.getTour(), filter)).map(GuideTour::getTour).collect(Collectors.toList());
    }

    public Tour getTour(Long tourId) {
        return tourRepository.findAll().stream().filter(tour -> Objects.equals(tour.getTourId(), tourId)).findFirst().orElse(null);
    }

    public List<Tour> getAllToursOnMarketplace(Filter filter) {
        return guideTourRepository.findAll().stream().map(GuideTour::getTour).filter(e -> e.isVisibleOnMarket() && filter(e, filter)).collect(Collectors.toList());
    }

    public Tour findById(Long tourId) {
        return tourRepository.findAll().stream().filter(tour -> tour.getTourId().equals(tourId)).findFirst().orElse(null);
    }

    public void updateTour(Tour tour) {
        final var existing = tourRepository.findAll().stream().filter(tour1 -> Objects.equals(tour1.getTourId(), tour.getTourId())).findFirst().orElse(null);
        if (existing != null) {
            tourRepository.save(
                    existing.toBuilder()
                            .name(tour.getName())
                            .price(tour.getPrice())
                            .city(tour.getCity())
                            .country(tour.getCountry())
                            .description(tour.getDescription())
                            .visibleOnMarket(tour.isVisibleOnMarket()).build());
        }
        final var startLocation = tour.getStartLocation();
        final var endLocation = tour.getEndLocation();
        locationService.updateLocation(startLocation);
        locationService.updateLocation(endLocation);
    }

    public void addTouristTour(User user, Tour tour) {
        final var touristTour = new TouristTour();
        touristTour.setTourist(user);
        touristTour.setTour(tour);
        touristTour.setLastUsed(System.currentTimeMillis());
        touristTourRepository.save(touristTour);
    }

    public void addGuideTour(User user, Tour tour) {
        final var guideTour = new GuideTour();
        guideTour.setGuide(user);
        guideTour.setTour(tour);
        guideTour.setCreateTime(System.currentTimeMillis());
        tourRepository.save(tour);
        guideTourRepository.save(guideTour);
    }

}
