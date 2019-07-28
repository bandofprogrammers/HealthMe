package com.healthme.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthme.model.User;

import com.healthme.model.entity.doctorsCalendar.WorkCalendar;
import com.healthme.model.entity.doctorsCalendar.WorkDay;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @JsonIgnore
    @NotBlank(message = "Gender must be selected")
    private String gender;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @JsonIgnore
    @PESEL
    private String pesel;

    @JsonIgnore
    @NotBlank(message = "Password is required")
    private String password;

    @JsonIgnore
    @NotBlank(message = "Email is required")
    @Email(message = "Wrong email format")
    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    private WorkCalendar workCalendar;

    @OneToMany(mappedBy = "doctor")
    private List<WorkDay> workDays;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Prescription> prescriptions;

    @NotNull
    @ManyToMany
    private List<DoctorSpecialization> doctorSpecializationList;

    //    TODO boolean stored as string in the mysql db, otherwise corrupted value is saved - fix to be found
    private String enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "doctors_roles",
            joinColumns = @JoinColumn(
                    name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public Doctor() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPesel() {
        return pesel;
    }

    @Override
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.valueOf(enabled);
    }

    @Override
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public List<DoctorSpecialization> getDoctorSpecializationList() {
        return doctorSpecializationList;
    }

    public void setDoctorSpecializationList(List<DoctorSpecialization> doctorSpecializationList) {
        this.doctorSpecializationList = doctorSpecializationList;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

}
