package com.example.marketour.model.entities;

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

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
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
    private Double price;

    @Column(name = "visible_on_market", nullable = false)
    private boolean visibleOnMarket;

    public static boolean filter(Tour tour, Filter filter) {
        return filter == null || ((filter.city == null || tour.city.equals(filter.city)) &&
                (filter.country == null || tour.country.equals(filter.country)) &&
                (filter.priceRange == null || filter.priceRange.contains(tour.price)));
    }
}
