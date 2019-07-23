package com.healthme.model;

import com.healthme.validatior.PasswordCheck;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@PasswordCheck
public class UserDto {

    private Long id;

    @NotBlank(message = "{UserDTOFirstName.notblank}")
    private String firstName;

    @NotBlank(message = "{UserDTOLastName.notblank}")
    private String lastName;

    //TODO
    private String gender;
    private String phoneNumber;

    @PESEL(message ="{UserDTOPesel.pesel}")
    private String pesel;

    @Email(message = "{UserDTOEmail.email}")
    @NotBlank(message = "{UserDTOEmail.notblank}")
    private String email;

    @NotBlank(message = "{UserDTOPass.notblank}")
    private String password;

    @NotBlank(message = "{UserDTOMatchingPass.notblank}")
    private String matchingPassword;

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
