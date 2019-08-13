package com.healthme.repository;

import com.healthme.model.entity.Visit;
import com.healthme.model.entity.doctorsCalendar.WorkDay;
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

    @Query(value="select * from visits join doctors on doctors.id=visits.doctor_id where visits.doctor_rating_id is null AND visits.patient_id=:patientId AND visits.local_date_time<:now", nativeQuery = true)
    List<Visit> findAllByPatientIdAndPastDate(@Param("now") String now, @Param("patientId") String patientId);

    @Query(value = "Select * from visits where local_date_time > :now AND patient_id=:patientId",nativeQuery = true)
    List<Visit> findVisitByPatientAndDateFromFutureOrToday(@Param("now") String now, @Param("patientId") String patientId);
}
