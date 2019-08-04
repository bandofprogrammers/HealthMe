package com.healthme.model.entity.doctorsCalendar;

import com.healthme.model.entity.Doctor;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private WorkCalendar workCalendar;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<WorkHour> workingHours;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }
}
