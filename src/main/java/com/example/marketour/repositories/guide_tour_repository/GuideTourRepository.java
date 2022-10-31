package com.example.marketour.repositories.guide_tour_repository;

import com.example.marketour.model.guide_tour.GuideTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideTourRepository extends JpaRepository<GuideTour, Long> {
}
