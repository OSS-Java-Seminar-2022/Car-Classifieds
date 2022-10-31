package com.example.marketour.repositories.tourist_tour_repository;

import com.example.marketour.model.tourist_tour.TouristTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristTourRepository extends JpaRepository<TouristTour, Long> {
}
