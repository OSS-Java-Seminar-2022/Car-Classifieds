package com.example.marketour.controllers;

import com.example.marketour.model.dtos.SaveTourBody;
import com.example.marketour.model.entities.*;
import com.example.marketour.services.LocationService;
import com.example.marketour.services.TourService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;
    private final LocationService locationService;

    public TourController(TourService tourService, LocationService locationService) {
        this.tourService = tourService;
        this.locationService = locationService;
    }


    @PostMapping("/saveTour")
    public ResponseEntity<Object> saveTour(HttpServletRequest request, @RequestBody String json) {
        if (request.getSession().getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }
        final var body = new Gson().fromJson(json, SaveTourBody.class);
        final Tour old = tourService.getTour(Long.valueOf(body.getTourId()));

        final var startLocation = old.getStartLocation();
        startLocation.setLatitude(Double.valueOf(body.getLatitudeStart()));
        startLocation.setLongitude(Double.valueOf(body.getLongitudeStart()));
        startLocation.setName(body.getStartLocationName());

        final var endLocation = old.getEndLocation();
        endLocation.setLatitude(Double.valueOf(body.getLatitudeEnd()));
        endLocation.setLongitude(Double.valueOf(body.getLongitudeEnd()));
        endLocation.setName(body.getEndLocationName());

        final var newTour = Tour.builder()
                .tourId(Long.valueOf(body.getTourId()))
                .name(body.getName())
                .description(body.getDescription())
                .city(City.valueOf(body.getCity()))
                .country(Country.valueOf(body.getCountry()))
                .price(Double.valueOf(body.getPrice()))
                .visibleOnMarket(body.isVisibleOnMarket())
                .startLocation(startLocation)
                .endLocation(endLocation).build();

        tourService.updateTour(newTour);
        return ResponseEntity.ok("Successfully updated tour!");
    }

    @GetMapping("/ofThisUser")
    public ResponseEntity<Object> getAllToursOfThisUser(HttpServletRequest request, Model model) {
        final var session = request.getSession();
        if (session != null) {
            final var user = (User) session.getAttribute("user");
            if (user != null) {
                final var filter = (Filter) model.getAttribute("filter");
                final List<Tour> result;
                if (user.getUserType() == UserType.tourist) {
                    result = tourService.getAllTouristsTours(user, filter);
                } else {
                    result = tourService.getAllGuideTours(user, filter);
                }
                if (result != null) {
                    return ResponseEntity.ok(result);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

    }

    @GetMapping("/getTour/{tourId}")
    public ResponseEntity<Object> getTour(HttpServletRequest request, @PathVariable String tourId) {
        final var session = request.getSession();
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }
        final var result = tourService.getTour(Long.valueOf(tourId));
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tour not found!");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllTours(HttpServletRequest request) {
        final var session = request.getSession();
        if (session.getAttribute("user") != null) {
            final var result = tourService.getAllTours();
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

    }

    @GetMapping("/market")
    public ResponseEntity<Object> getAllToursOnMarket(HttpServletRequest request, Model model) {
        final var session = request.getSession();
        if (session.getAttribute("user") != null) {
            final var filter = (Filter) model.getAttribute("filter");
            final var result = tourService.getAllToursOnMarketplace(filter);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tours not found for that user!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

    }

    @GetMapping("/delete/{tourId}")
    public ResponseEntity<Object> removeTour(HttpServletRequest request, @PathVariable String tourId) {
        if (request.getSession().getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }
        tourService.deleteTour(Long.valueOf(tourId));
        return ResponseEntity.ok("Successfully removed tour!");
    }
}
