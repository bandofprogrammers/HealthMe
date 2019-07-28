package com.healthme.model.entity.doctorsCalendar;

import com.healthme.model.entity.Doctor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToMany
    private Doctor doctor;

    @OneToMany
    private List<WorkHour> workingHours;
}
