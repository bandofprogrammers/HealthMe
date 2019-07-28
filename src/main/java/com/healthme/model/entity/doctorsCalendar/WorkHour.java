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

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;
}
