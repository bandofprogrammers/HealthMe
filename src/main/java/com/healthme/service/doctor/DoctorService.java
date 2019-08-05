package com.healthme.service.doctor;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;
import com.healthme.model.entity.Visit;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {


    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DoctorService(VisitRepository visitRepository, DoctorRepository doctorRepository,PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Visit> findAllVisitByDoctorIdForCurrentDay(Long doctorId){
        LocalDateTime now = LocalDateTime.now();
        String nowOnlyDate = now.toString().substring(0,10).trim();
        return visitRepository.findAllVisitByDoctorIdForCurrentDay(nowOnlyDate, doctorId);
    }

    public Doctor findDoctorByEmail(String doctorEmail){
        return doctorRepository.findByEmail(doctorEmail);
    }

    public List<Patient> findAllPatientsByDoctorAndVisits(Long doctorId) {
       return patientRepository.findAllPatientsByDoctorAndVisits(doctorId);
    }

    public List<Doctor> findAllBySpecializationInternistWithRating() {
        return doctorRepository.findAllBySpecInternistWithRating();
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
