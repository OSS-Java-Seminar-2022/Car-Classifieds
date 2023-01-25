package com.example.marketour.model.dtos;

import com.example.marketour.model.entities.City;
import com.example.marketour.model.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourPageRequestBody {
    private Integer page;

    private String title;

    private String body;

    private Double longitude;

    private Double latitude;

    private String locationName;

    private Country country;
    private City city;
    private String imageDescription;
    private String audioDescription;
}
