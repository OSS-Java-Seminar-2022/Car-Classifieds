package com.example.marketour.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tourist_tour")
@Getter
@Setter
public class TouristTour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourist_tour_id")
    private Long touristTourId;

    @OneToOne
    @JoinColumn(name = "tourist_id", referencedColumnName = "user_id", nullable = false)
    private User tourist;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "last_tour_page_id", referencedColumnName = "tour_page_id")
    private TourPage progress;

    //in milliseconds since Epoch
    @Column(name = "last_used")
    private Long lastUsed;

    //in milliseconds since Epoch
    @Column(name = "archived")
    private Long archived;
}