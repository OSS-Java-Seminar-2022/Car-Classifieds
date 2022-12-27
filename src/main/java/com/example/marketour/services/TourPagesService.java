package com.example.marketour.services;

import com.example.marketour.model.entities.*;
import com.example.marketour.repositories.TourPageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TourPagesService {
    private final TourPageRepository tourPageRepository;

    public TourPagesService(TourPageRepository tourPageRepository) {
        this.tourPageRepository = tourPageRepository;
    }

    public List<TourPage> getAllTourPages(Long tourId) {
        return tourPageRepository.findAll().stream().filter(tourPage -> Objects.equals(tourPage.getTour().getTourId(), tourId)).collect(Collectors.toList());
    }

    public void insertNewTourPage(Tour tour, Integer page, String title, String body, Image image, Audio audio, Location location) {
        final var newTourPage = new TourPage();
        newTourPage.setPage(page);
        newTourPage.setTour(tour);
        newTourPage.setBody(body);
        newTourPage.setAudio(audio);
        newTourPage.setLocation(location);
        newTourPage.setImage(image);
        newTourPage.setTitle(title);
        tourPageRepository.save(newTourPage);
    }

    public void removeTourPage(TourPage tourPage) {
        tourPageRepository.delete(tourPage);
    }
}
