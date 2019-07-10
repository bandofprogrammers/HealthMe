package com.healthme.model;

import com.healthme.entity.DoctorSpecialization;
import com.healthme.entity.WorkCalendar;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="doctors")
public class Doctor implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;

    @NotBlank
    private String phoneNumber;

    @PESEL
    private String pesel;

    @NotBlank
    private String password;

    @Email
    private String email;

    @NotNull
    @OneToOne
    private WorkCalendar workCalendar;

    @NotNull
    @ManyToMany
    private List<DoctorSpecialization> doctorSpecializationList;

    //    TODO boolean stored as string in the mysql db, otherwise corrupted value is saved - fix to be found
    private String enabled;

    @ManyToMany(fetch = FetchType.EAGER)
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
}
