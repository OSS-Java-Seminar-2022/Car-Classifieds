package com.example.marketour.model.tourist_tour;

import com.example.marketour.model.tour.Tour;
import com.example.marketour.model.tour_page.TourPage;
import com.example.marketour.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "tourist_tour")
public class TouristTour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourist_tour_id")
    private int touristTourId;

    @OneToOne
    @JoinColumn(name = "tourist_id", referencedColumnName = "user_id", nullable = false)
    private User tourist;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "tour_page_id", nullable = false)
    private TourPage progress;

    //in milliseconds since Epoch
    @Column(name = "last_used")
    private Long lastUsed;

    //in milliseconds since Epoch
    @Column(name = "archived")
    private Long archived;
}