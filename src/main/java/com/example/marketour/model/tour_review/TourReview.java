package com.example.marketour.model.tour_review;

import com.example.marketour.model.tour.Tour;
import com.example.marketour.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "tour_review")
public class TourReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tour_review_id")
    private int tourReviewId;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "text", nullable = false)
    private String text;


    @Column(name = "rate")
    private int rate;

    //millis since epoch
    @Column(name = "time", nullable = false)
    private Long time;
}
