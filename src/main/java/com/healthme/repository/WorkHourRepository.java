package com.healthme.repository;

import com.healthme.model.entity.doctorsCalendar.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
}
