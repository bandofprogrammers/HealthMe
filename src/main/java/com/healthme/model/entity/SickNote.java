package com.healthme.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
public class SickNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @Pattern(regexp = "[1-4]{1}")
    private String insuredIn;

    @FutureOrPresent
    private LocalDate creationDate;

    @Pattern(regexp = "[0-9]{3}")
    private String statisticalNumberOfDisease;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inabilityToWorkFrom;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inabilityToWorkTo;

    private int daysInHospital;

    @ManyToOne
    private Doctor doctor;

    @Pattern(regexp = "[1-3]{1}")
    private String kinshipCode;

    @Pattern(regexp = "[1-2]{1}")
    private String medicalIndications;

    @Pattern(regexp = "[ABCDE]{1,4}")
    private String codes;

    @OneToOne
    private Visit visit;

    public SickNote() {
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

    public String getInsuredIn() {
        return insuredIn;
    }

    public void setInsuredIn(String insuredIn) {
        this.insuredIn = insuredIn;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatisticalNumberOfDisease() {
        return statisticalNumberOfDisease;
    }

    public void setStatisticalNumberOfDisease(String statisticalNumberOfDisease) {
        this.statisticalNumberOfDisease = statisticalNumberOfDisease;
    }

    public LocalDate getInabilityToWorkFrom() {
        return inabilityToWorkFrom;
    }

    public void setInabilityToWorkFrom(LocalDate inabilityToWorkFrom) {
        this.inabilityToWorkFrom = inabilityToWorkFrom;
    }

    public LocalDate getInabilityToWorkTo() {
        return inabilityToWorkTo;
    }

    public void setInabilityToWorkTo(LocalDate inabilityToWorkTo) {
        this.inabilityToWorkTo = inabilityToWorkTo;
    }

    public int getDaysInHospital() {
        return daysInHospital;
    }

    public void setDaysInHospital(int daysInHospital) {
        this.daysInHospital = daysInHospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getKinshipCode() {
        return kinshipCode;
    }

    public void setKinshipCode(String kinshipCode) {
        this.kinshipCode = kinshipCode;
    }

    public String getMedicalIndications() {
        return medicalIndications;
    }

    public void setMedicalIndications(String medicalIndications) {
        this.medicalIndications = medicalIndications;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
