package com.example.marketour.repositories;

import com.example.marketour.model.entities.TourReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourReviewRepository extends JpaRepository<TourReview, Long> {

}
