package com.healthme.config;

import com.healthme.model.Patient;
import com.healthme.model.Role;
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
        Patient patient = new Patient();
        patient.setFirstName("Test_Admin");
        patient.setLastName("Test_Admin");
        patient.setPassword(passwordEncoder.encode("test"));
        patient.setEmail("test_admin@test.com");
        patient.setRoles(Arrays.asList(adminRole));
        patient.setEnabled("true");
        patientRepository.save(patient);

        Role patientRole = roleRepository.findByName("ROLE_PATIENT");
        patient = new Patient();
        patient.setFirstName("Test_User");
        patient.setLastName("Test_User");
        patient.setPassword(passwordEncoder.encode("test"));
        patient.setEmail("test_patient@test.com");
        patient.setRoles(Arrays.asList(patientRole));
        patient.setEnabled("true");
        patientRepository.save(patient);

        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        patient = new Patient();
        patient.setFirstName("Test_Doctor");
        patient.setLastName("Test_Doctor");
        patient.setPassword(passwordEncoder.encode("test"));
        patient.setEmail("test_doctor@test.com");
        patient.setRoles(Arrays.asList(doctorRole));
        patient.setEnabled("true");
        patientRepository.save(patient);

        alreadySetup = true;
    }

//    TODO read what is going on with this annotation
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