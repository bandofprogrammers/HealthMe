package com.healthme.service;

import com.healthme.model.Patient;
import com.healthme.model.UserDto;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Patient registerNewUserAccount(UserDto accountDto) throws NullPointerException {

        if (emailExists(accountDto.getEmail())) {
            throw new NullPointerException("There is an account with that email address: " + accountDto.getEmail());
        }
        Patient patient = new Patient();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        patient.setFirstName(accountDto.getFirstName());
        patient.setLastName(accountDto.getLastName());
        patient.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        patient.setEmail(accountDto.getEmail());
        patient.setEnabled("false");
        patient.setRoles(Arrays.asList(roleRepository.findByName("ROLE_PATIENT")));
        return patientRepository.save(patient);
    }

    private boolean emailExists(String email) {
        Patient patient = patientRepository.findByEmail(email);

        if (patient != null) {
            return true;
        }
        return false;
    }

}
