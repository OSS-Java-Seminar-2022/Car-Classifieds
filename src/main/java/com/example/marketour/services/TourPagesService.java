package com.example.marketour.services;

import com.example.marketour.model.entities.*;
import com.example.marketour.repositories.TourPageRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void reorder(List<Long> newIds, Long tourId) {
        final var old = tourPageRepository.findAll().stream()
                .filter(tourPage -> Objects.equals(tourPage.getTour().getTourId(), tourId))
                .collect(Collectors.toList());
        Logger.getGlobal().log(Level.INFO, old.stream().sorted(Comparator.comparing(TourPage::getTourPageId)).map(tourPage -> Map.entry(tourPage.getTourPageId(), tourPage.getPage())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString());
        final var newPages = newIds.stream().map(aLong -> {
            final var occurrence = old.stream().filter(tourPage -> Objects.equals(tourPage.getTourPageId(), aLong)).findFirst().orElse(null);
            if (occurrence != null) {
                occurrence.setPage(newIds.indexOf(aLong));
            }
            return occurrence;
        }).collect(Collectors.toList());

        Logger.getGlobal().log(Level.INFO, newPages.stream().sorted(Comparator.comparing(TourPage::getTourPageId)).map(tourPage -> Map.entry(tourPage.getTourPageId(), tourPage.getPage())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString());
        tourPageRepository.saveAll(newPages);
    }


    public TourPage getFirstPage(Long tourId) {
        return tourPageRepository.findAll().stream().filter(tourPage -> Objects.equals(tourPage.getTour().getTourId(), tourId) && tourPage.getPage() == 0).findFirst().orElse(null);
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
