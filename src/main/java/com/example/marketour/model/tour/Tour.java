package com.example.marketour.model.tour;

import com.example.marketour.model.city.City;
import com.example.marketour.model.country.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tour")
@Getter
@Setter
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tour_id")
    private int tourId;

    @Column(name = "start_latitude", nullable = false)
    private int startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private int startLongitude;

    @Column(name = "end_latitude", nullable = false)
    private int endLatitude;

    @Column(name = "end_longitude", nullable = false)
    private int endLongitude;

    @Column(name = "country", nullable = false)
    @Enumerated
    private Country country;

    @Column(name = "city", nullable = false)
    @Enumerated
    private City city;

    //in meters
    @Column(name = "length")
    private int length;

    //in minutes
    @Column(name = "expected_duration")
    private int expectedDuration;

    @Column(name = "price", nullable = false)
    private double tokens;

    @Column(name = "visible_on_market", nullable = false)
    private boolean visibleOnMarket;

}
