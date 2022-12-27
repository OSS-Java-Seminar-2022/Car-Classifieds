package com.example.marketour.controllers;

import com.example.marketour.model.entities.Image;
import com.example.marketour.model.entities.TourPage;
import com.example.marketour.services.TourPagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final TourPagesService tourPagesService;

    public ImageController(TourPagesService tourPagesService) {
        this.tourPagesService = tourPagesService;
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<List<Image>> getTourImages(@PathVariable Long tourId) {
        return ResponseEntity.ok(tourPagesService.getAllTourPages(tourId).stream().map(TourPage::getImage).collect(Collectors.toList()));
    }
}
