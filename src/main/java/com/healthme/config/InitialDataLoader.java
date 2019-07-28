package com.healthme.config;

import com.healthme.model.entity.*;
import com.healthme.model.entity.doctorsCalendar.WorkCalendar;
import com.healthme.model.entity.doctorsCalendar.WorkDay;
import com.healthme.model.entity.doctorsCalendar.WorkHour;
import com.healthme.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        createNewDoctorSpecializationIfNotFound("Internist");
        createNewDoctorSpecializationIfNotFound("Laryngologist");
        createNewDoctorSpecializationIfNotFound("Cardiologist");
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

        doctor.setDoctorSpecializationList(doctorSpecialization);

        WorkDay workDay = new WorkDay();
        workDay.setDate(LocalDate.parse("2019-07-29"));
        workDay.setDoctor(doctor);
        workDay.setWorkCalendar(workCalendar);
        WorkHour workHour = new WorkHour();
        workHour.setHour("8:00");
        workHour.setDoctor(doctor);
        workHour.setDoctor(doctor);
        workHour.setWorkDay(workDay);

        WorkHour workHour1 = new WorkHour();
        workHour1.setHour("9:00");
        workHour1.setDoctor(doctor);
        workHour1.setDoctor(doctor);
        workHour1.setWorkDay(workDay);

        List<WorkHour> workHours = new ArrayList<>();

        workHours.add(workHour);
        workHours.add(workHour1);

        workDay.setWorkingHours(workHours);
        workCalendar.setDaysOfWork(Arrays.asList(workDay));
        workCalendar.setDoctor(doctor);
        doctor.setWorkCalendar(workCalendar);


        doctorRepository.save(doctor);

//      Test laryngologist

        List<DoctorSpecialization> laryngologist = new ArrayList<>();
        laryngologist.add(doctorSpecializationRepository.findByName("Laryngologist"));
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Test_Doctor2");
        doctor2.setLastName("Test_Doctor2");
        doctor2.setPassword(passwordEncoder.encode("test"));
        doctor2.setEmail("test_doctor2@test.com");
        doctor2.setRoles(Arrays.asList(doctorRole));
        doctor2.setEnabled("true");
        doctor2.setGender("Male");
        doctor2.setPhoneNumber("111111111");
        doctor2.setPesel("03241037648");
        doctor2.setWorkCalendar(workCalendar);
        doctor2.setDoctorSpecializationList(laryngologist);
        doctorRepository.save(doctor2);


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