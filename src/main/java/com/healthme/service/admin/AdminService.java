package com.healthme.service.admin;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;
import com.healthme.model.entity.Role;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void approvePatientAccountById(Long id) {
        Patient patient = patientRepository.getOne(id);
        patient.setEnabled("true");
        patientRepository.save(patient);
    }

    public void removePatientById(Long id) {
        Patient patient = patientRepository.getOne(id);
        patientRepository.delete(patient);
    }

    public List<Doctor> findAllDoctors() {
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        List<Doctor> doctors = doctorRepository.findAllDoctors(doctorRole);
        return doctors;
    }

    public void removeDoctorById(Long id) {
        Doctor doctor = doctorRepository.getOne(id);
        doctorRepository.delete(doctor);
    }

    public void saveChangesInDoctorData(Doctor doctor) {
        //TODO controller should return enabled and role so we do not have to set it here
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctorRepository.save(doctor);
    }

    public void addNewDoctor(Doctor doctor) {
        String password = doctor.getPassword();
        doctor.setPassword(passwordEncoder.encode(password));
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.getOne(id);
    }

    public List<Patient> getAllNotEnabledPatients() {
        return patientRepository.getAllNotEnabled();
    }
}
