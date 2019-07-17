package com.healthme.config;

import com.healthme.model.entity.*;
import com.healthme.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @Autowired
    private WorkCalendarRepository workCalendarRepository;

    @Autowired
    private VisitRepository visitRepository;

    public InitialDataLoader() {
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_SUPER_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_PATIENT");
        createRoleIfNotFound("ROLE_DOCTOR");
        createNewDoctorSpecializationIfNotFound("internist");
        createNewDoctorSpecializationIfNotFound("laryngologist");
        createNewWorkCalendarIfNotFound("2019");

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
        patient.setPesel("82032423172");
        patient.setGender("Male");
        patient.setEnabled("true");
        patient.setPhoneNumber("879456123");
        patientRepository.save(patient);

        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        List<DoctorSpecialization> doctorSpecialization = doctorSpecializationRepository.findAll();
        WorkCalendar workCalendar = workCalendarRepository.findByName("2019");
        Doctor doctor = new Doctor();
        doctor.setFirstName("Test_Doctor");
        doctor.setLastName("Test_Doctor");
        doctor.setPassword(passwordEncoder.encode("test"));
        doctor.setEmail("test_doctor@test.com");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctor.setEnabled("true");
        doctor.setGender("Male");
        doctor.setPhoneNumber("777777777");
        doctor.setPesel("75012097612");
        doctor.setWorkCalendar(workCalendar);
        doctor.setDoctorSpecializationList(doctorSpecialization);
        doctorRepository.save(doctor);

        Visit visit = new Visit();
        visit.setDoctor(doctorRepository.findByEmail("test_doctor@test.com"));
        visit.setPatient(patientRepository.findByEmail("test_patient@test.com"));
        LocalDateTime now = LocalDateTime.now();
        visit.setLocalDateTime(now);
        visit.setVisitDescription("visit description");
        visitRepository.save(visit);

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

    @Transactional
    private DoctorSpecialization createNewDoctorSpecializationIfNotFound(
            String name) {
        DoctorSpecialization doctorSpecialization = doctorSpecializationRepository.findByName(name);
        if (doctorSpecialization == null) {
            doctorSpecialization = new DoctorSpecialization(name);
            doctorSpecializationRepository.save(doctorSpecialization);
        }
        return doctorSpecialization;
    }

    @Transactional
    private WorkCalendar createNewWorkCalendarIfNotFound(
            String name) {
        WorkCalendar workCalendar = workCalendarRepository.findByName(name);
        if (workCalendar == null) {
            workCalendar = new WorkCalendar(name);
            workCalendarRepository.save(workCalendar);
        }
        return workCalendar;
    }
}