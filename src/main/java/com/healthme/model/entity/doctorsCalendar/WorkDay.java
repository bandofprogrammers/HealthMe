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

    @OneToOne
    private Doctor doctor;

    @OneToMany(mappedBy = "workDay")
    private List<WorkHour> workingHours;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<WorkHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkHour> workingHours) {
        this.workingHours = workingHours;
    }
}
