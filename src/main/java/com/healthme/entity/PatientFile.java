package com.healthme.entity;

import javax.persistence.*;

@Entity
@Table(name = "patientsFiles")
public class PatientFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Patient patient;

    public PatientFile(Patient patient) {
        this.patient = patient;
    }

    public PatientFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
