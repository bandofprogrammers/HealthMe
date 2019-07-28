package com.healthme.model.entity.doctorsCalendar;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;

import javax.persistence.*;

@Entity
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    TODO maybe change format here from string to time
    private String hour;

    @ManyToOne
    private WorkDay workDay;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
