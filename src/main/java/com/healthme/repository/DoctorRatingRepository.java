package com.healthme.repository;

import com.healthme.model.entity.DoctorRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Long> {
}
