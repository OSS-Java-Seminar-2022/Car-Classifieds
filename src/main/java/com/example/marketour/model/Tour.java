package com.example.marketour.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tour")
@Getter
@Setter
@ToString
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tour_id")
    private Long tourId;

    @OneToOne
    @JoinColumn(name = "start_location_id", nullable = false, referencedColumnName = "location_id")
    private Location startLocation;

    @OneToOne
    @JoinColumn(name = "end_location_id", nullable = false, referencedColumnName = "location_id")
    private Location endLocation;

    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "city", nullable = false)
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name = "price", nullable = false)
    private double tokens;

    @Column(name = "visible_on_market", nullable = false)
    private boolean visibleOnMarket;

}
