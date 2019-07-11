package com.healthme.config;

import com.healthme.model.entity.Admin;
import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;
import com.healthme.model.entity.Role;
import com.healthme.repository.AdminRepository;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_SUPER_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_PATIENT");
        createRoleIfNotFound("ROLE_DOCTOR");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Admin admin = new Admin();
        admin.setFirstName("Test_Admin");
        admin.setLastName("Test_Admin");
        admin.setPassword(passwordEncoder.encode("test"));
        admin.setEmail("test_admin@test.com");
        admin.setRoles(Arrays.asList(adminRole));
        admin.setEnabled("true");
        adminRepository.save(admin);

        Role patientRole = roleRepository.findByName("ROLE_PATIENT");
        Patient patient = new Patient();
        patient.setFirstName("Test_User");
        patient.setLastName("Test_User");
        patient.setPassword(passwordEncoder.encode("test"));
        patient.setEmail("test_patient@test.com");
        patient.setRoles(Arrays.asList(patientRole));
        patient.setEnabled("true");
        patientRepository.save(patient);

        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        Doctor doctor = new Doctor();
        doctor.setFirstName("Test_Doctor");
        doctor.setLastName("Test_Doctor");
        doctor.setPassword(passwordEncoder.encode("test"));
        doctor.setEmail("test_doctor@test.com");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctor.setEnabled("true");
        doctorRepository.save(doctor);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(
            String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}