package com.healthme.repository;

import com.healthme.entity.WorkCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkCalendarRepository extends JpaRepository<WorkCalendar, Long> {
}
