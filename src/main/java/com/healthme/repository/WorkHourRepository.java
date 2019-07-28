package com.healthme.repository;

import com.healthme.model.entity.doctorsCalendar.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
    @Query("SELECT h from WorkHour h WHERE h.doctor.id=:id and h.workDay.date=:date")
    List<WorkHour> getAvailableTermsByDoctorIdAndDate(@Param("id") Long doctorId, @Param("date") String date);
}