package com.example.marketour.controllers;

import com.example.marketour.model.dtos.ReorderBody;
import com.example.marketour.model.dtos.TourPageRequestBody;
import com.example.marketour.model.entities.*;
import com.example.marketour.services.*;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tourPages")
public class TourPageController {

    private final TourPagesService tourPagesService;
    private final LocationService locationService;
    private final ImageService imageService;
    private final AudioService audioService;
    private final TourService tourService;

    public TourPageController(TourPagesService tourPagesService, LocationService locationService, ImageService imageService, AudioService audioService, TourService tourService) {
        this.tourPagesService = tourPagesService;
        this.locationService = locationService;
        this.imageService = imageService;
        this.audioService = audioService;
        this.tourService = tourService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<TourPage>> getAllTourPages(@PathVariable Long tourId) {
        return ResponseEntity.ok(tourPagesService.getAllTourPages(tourId));
    }

    @PostMapping("/reorder/{tourId}")
    public ResponseEntity<Object> reorder(@PathVariable String tourId, HttpServletRequest request, @RequestBody String json) {
        final ReorderBody reorderBody = new Gson().fromJson(json, ReorderBody.class);
        if (request.getSession() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in!");
        }
        try {
            tourPagesService.reorder(reorderBody.getIds(), Long.valueOf(tourId));
            return ResponseEntity.ok("Successfully reordered!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTourPage(@RequestParam("tourPage") String tourPageRequestBodyJson,
                                              @RequestParam(name = "image", required = false) MultipartFile imagePart,
                                              @RequestParam(name = "audio", required = false) MultipartFile audioPart,
                                              HttpServletRequest request) {
        TourPageRequestBody tourPageRequestBody = new Gson().fromJson(tourPageRequestBodyJson, TourPageRequestBody.class);
        if (request.getSession() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in!");
        } else if (((User) request.getSession().getAttribute("user")).getUserType() != UserType.guide) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in as a guide!");
        }
        final var tour = (Tour) request.getSession().getAttribute("tour");
        Image image = null;
        Audio audio = null;
        final User user = (User) request.getSession().getAttribute("user");

        final Location location;
        Location tempLocation = locationService.getExisting(tourPageRequestBody.getLongitude(), tourPageRequestBody.getLatitude(), tourPageRequestBody.getLocationName());
        if (tempLocation == null) {
            location = locationService.addLocation(tourPageRequestBody.getLongitude(), tourPageRequestBody.getLatitude(), tourPageRequestBody.getLocationName());
        } else {
            location = tempLocation;
        }
        if (tourPageRequestBody.getPage() == 0) {
            tour.setCountry(tourPageRequestBody.getCountry());
            tour.setCity(tourPageRequestBody.getCity());
            tour.setStartLocation(location);
            tour.setEndLocation(location);
            tourService.addGuideTour(user, tour);
            request.removeAttribute("tour");
        }
        try {
            if (imagePart != null) {
                image = imageService.addImage(tourPageRequestBody.getImageDescription(), imagePart.getBytes());
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error saving image!");
            throw new RuntimeException(e);
        }
        try {
            if (audioPart != null) {
                audio = audioService.addAudio(tourPageRequestBody.getAudioDescription(), audioPart.getBytes());
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error saving audio!");
            throw new RuntimeException(e);
        }
        tourPagesService.insertNewTourPage(tour, tourPageRequestBody.getPage(), tourPageRequestBody.getTitle(), tourPageRequestBody.getBody(), image, audio, location);
        return ResponseEntity.ok("Successfully added tour page!");
    }
}
