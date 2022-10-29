package com.example.marketour.model.tour_page;

import com.example.marketour.model.audio.Audio;
import com.example.marketour.model.image.Image;
import com.example.marketour.model.tour.Tour;

import javax.persistence.*;

@Entity
@Table(name = "tour_page")
public class TourPage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tour_page_id")
    private int tourPageId;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(name = "page", nullable = false)
    private int page;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body")
    private String body;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne
    @JoinColumn(name = "audio_id")
    private Audio audio;

    @Column(name = "longitude")
    private int longitude;

    @Column(name = "latitude")
    private int latitude;

    @Column(name = "completed", nullable = false)
    private boolean completed;

}
