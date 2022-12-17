package com.example.marketour.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Getter
@ToString
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "longitude", nullable = false)
    private Long longitude;

    @Column(name = "latitude", nullable = false)
    private Long latitude;

    @Column(name = "name", nullable = false)
    private String name;
}
