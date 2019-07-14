package com.healthme.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
public class SickNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @Pattern(regexp = "[1-4]{1}")
    private int insuredIn;

    @FutureOrPresent
    private LocalDate creationDate;

    @Pattern(regexp = "[0-9]{3}")
    private int statisticalNumberOfDisease;

    private LocalDate inabilityToWorkFrom;

    @Future
    private LocalDate inabilityToWorkTo;

    private int daysInHospital;

    private int doctorId;

    @Pattern(regexp = "[1-3]{1}")
    private int kinshipCode;

    @Pattern(regexp = "[1-2]{1}")
    private int medicalIndications;

    @Pattern(regexp = "[ABCDE]{1,4}")
    private String codes;

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

    public int getInsuredIn() {
        return insuredIn;
    }

    public void setInsuredIn(int insuredIn) {
        this.insuredIn = insuredIn;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getStatisticalNumberOfDisease() {
        return statisticalNumberOfDisease;
    }

    public void setStatisticalNumberOfDisease(int statisticalNumberOfDisease) {
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getKinshipCode() {
        return kinshipCode;
    }

    public void setKinshipCode(int kinshipCode) {
        this.kinshipCode = kinshipCode;
    }

    public int getMedicalIndications() {
        return medicalIndications;
    }

    public void setMedicalIndications(int medicalIndications) {
        this.medicalIndications = medicalIndications;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }
}
