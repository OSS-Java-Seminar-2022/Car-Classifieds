package com.example.marketour.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tour_page")
@Getter
@Setter
@ToString
public class TourPage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tour_page_id")
    private Long tourPageId;

    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(name = "page", nullable = false)
    private Integer page;

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

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;


}
