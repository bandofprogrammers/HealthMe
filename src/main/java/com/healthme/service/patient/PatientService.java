package com.healthme.service.patient;

import com.healthme.model.entity.Admin;
import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;
import com.healthme.model.UserDto;
import com.healthme.repository.AdminRepository;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Patient registerNewPatientAccount(UserDto accountDto) throws NullPointerException {

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
        Doctor doctor = doctorRepository.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);

        if (patient != null ||
                admin != null ||
                doctor != null) {
            return true;
        }
        return false;
    }

}
