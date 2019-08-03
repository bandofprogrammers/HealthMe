package com.healthme.repository;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.DoctorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Long> {

    @Query("SELECT r FROM DoctorRating r WHERE r.doctor=:doctor")
    List<DoctorRating> getRatingsForDoctor(@Param("doctor") Doctor doctor);
}
