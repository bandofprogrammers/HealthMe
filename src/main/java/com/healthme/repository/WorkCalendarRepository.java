package com.healthme.repository;

import com.healthme.model.entity.WorkCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkCalendarRepository extends JpaRepository<WorkCalendar, Long> {

    @Query("SELECT w FROM WorkCalendar w WHERE w.name=:name")
    WorkCalendar findByName(@Param("name") String name);
}
