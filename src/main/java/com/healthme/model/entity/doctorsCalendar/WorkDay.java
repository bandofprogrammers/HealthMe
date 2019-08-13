package com.healthme.model.entity.doctorsCalendar;

import com.healthme.model.entity.Doctor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private WorkCalendar workCalendar;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<WorkHour> workingHours;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
