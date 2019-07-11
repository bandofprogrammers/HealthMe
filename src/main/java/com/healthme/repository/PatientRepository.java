package com.healthme.repository;

import com.healthme.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("SELECT p FROM Patient p WHERE p.email=:email")
    Patient findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Patient p WHERE p.enabled='false'")
    List<Patient> getAllNotEnabled();

}
