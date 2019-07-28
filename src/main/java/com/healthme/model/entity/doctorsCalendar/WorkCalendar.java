package com.healthme.model.entity.doctorsCalendar;

import com.healthme.model.entity.Doctor;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<WorkDay> daysOfWork;

    @OneToOne
    private Doctor doctor;

    public WorkCalendar() {
    }

    public WorkCalendar(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkDay> getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(List<WorkDay> daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
