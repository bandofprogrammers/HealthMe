package com.healthme.model.entity;

import com.healthme.model.entity.doctorsCalendar.WorkHour;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Doctor doctor;

    @NotNull
    @OneToOne
    private Patient patient;

    @OneToMany
    private List<Prescription> prescriptionList;

    @Column(columnDefinition = "VARCHAR(500)")
    private String recomendations;

    @NotBlank(message = "{visitVisitDescription.notblank}")
    @Column(columnDefinition = "VARCHAR(500)")
    private String visitDescription;

    @OneToOne
    private SickNote sickNote;

    private LocalDateTime localDateTime;

    @OneToOne
    private DoctorRating doctorRating;

    public Visit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public String getRecomendations() {
        return recomendations;
    }

    public void setRecomendations(String recomendations) {
        this.recomendations = recomendations;
    }

    public String getVisitDescription() {
        return visitDescription;
    }

    public void setVisitDescription(String visitDescription) {
        this.visitDescription = visitDescription;
    }

    public SickNote getSickNote() {
        return sickNote;
    }

    public void setSickNote(SickNote sickNote) {
        this.sickNote = sickNote;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public DoctorRating getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(DoctorRating doctorRating) {
        this.doctorRating = doctorRating;
    }
}
