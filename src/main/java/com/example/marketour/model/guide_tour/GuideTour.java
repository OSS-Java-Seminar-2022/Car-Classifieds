package com.example.marketour.model.guide_tour;

import com.example.marketour.model.tour.Tour;
import com.example.marketour.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "guide_tour")
public class GuideTour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guide_tour_id")
    private int guideTourId;

    @OneToOne
    @JoinColumn(name = "guide_id", referencedColumnName = "user_id", nullable = false)
    private User guide;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false, unique = true)
    private Tour tour;

    //in milliseconds
    @Column(name = "create_time", nullable = false)
    private Long createTime;

    //in milliseconds
    @Column(name = "expiration_time")
    private Long expirationTime;
}
