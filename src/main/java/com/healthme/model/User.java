package com.healthme.model;

import com.healthme.model.entity.Role;

import java.util.List;

public interface User {

    Long getId();

    void setId(Long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getGender();

    void setGender(String gender);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getPesel();

    void setPesel(String pesel);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    boolean isEnabled();

    void setEnabled(String enabled);

    List<Role> getRoles();

    void setRoles(List<Role> roles);

}
