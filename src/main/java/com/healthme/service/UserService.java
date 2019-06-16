package com.healthme.service;

import com.healthme.entity.Patient;
import com.healthme.model.UserDto;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient registerNewUserAccount(UserDto accountDto) throws NullPointerException{
        if (emailExists(accountDto.getEmail())) {
            throw new NullPointerException();
        }
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        Patient patient = new Patient();
        patient.setFirstName(accountDto.getFirstName());
        patient.setLastName(accountDto.getLastName());
        patient.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        patient.setEmail(accountDto.getEmail());
        return patientRepository.save(patient);
    }

    private boolean emailExists(String email) {
        Patient patient = patientRepository.findFirstByEmail(email);

        if (patient != null) {
            return true;
        }
        return false;
    }

}
