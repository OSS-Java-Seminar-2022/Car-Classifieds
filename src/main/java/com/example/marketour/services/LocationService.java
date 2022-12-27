package com.example.marketour.services;

import com.example.marketour.model.entities.Location;
import com.example.marketour.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.Math.abs;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Double longitude, Double latitude, String name) {
        final var newLocation = new Location();
        newLocation.setLatitude(latitude);
        newLocation.setLongitude(longitude);
        newLocation.setName(name);
        return locationRepository.save(newLocation);
    }

    public Location getExisting(Double longitude, Double latitude, String name) {
        return locationRepository.findAll().stream().filter(location ->
                Objects.equals(location.getName(), name) ||
                        (abs(location.getLatitude() - latitude) <= 0.001 &&
                                abs(location.getLongitude() - longitude) <= 0.001)).findFirst().orElse(null);
    }
}
