package com.healthme.repository;

import com.healthme.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(value = "SELECT * from visits where local_date_time LIKE :nowOnlyDate% AND doctor_id=:doctorId", nativeQuery = true)
    List<Visit> findAllVisitByDoctorIdForCurrentDay(@Param("nowOnlyDate") String nowOnlyDate, @Param("doctorId") Long doctorId);

    List<Visit> findAllByPatientIdOrderByLocalDateTimeDesc(Long id);
}
