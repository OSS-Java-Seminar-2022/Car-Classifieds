package com.example.marketour.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourPageRequestBody {
    private Long tourId;

    private Integer page;

    private String title;

    private String body;

    private Double longitude;

    private Double latitude;

    private String locationName;

    private String imageDescription;
    private String audioDescription;
}
