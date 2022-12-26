package com.example.marketour.model.entities;

import lombok.Data;
import org.springframework.data.domain.Range;

@Data
public class Filter {
    final Country country;
    final City city;

    final Range<Double> priceRange;

    public Filter(Country country, City city, Range<Double> priceRange) {
        this.country = country;
        this.city = city;
        this.priceRange = priceRange;
    }
}
