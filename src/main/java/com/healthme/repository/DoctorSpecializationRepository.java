package com.healthme.repository;

import com.healthme.entity.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {

    @Query("SELECT d FROM DoctorSpecialization d WHERE d.name=:name")
    DoctorSpecialization findByName(@Param("name") String name);
}
