package com.healthme.service.doctor;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Visit;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {


    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(VisitRepository visitRepository, DoctorRepository doctorRepository) {
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Visit> findAllVisitByDoctorIdForCurrentDay(Long doctorId){
        LocalDateTime now = LocalDateTime.now();
        String nowOnlyDate = now.toString().substring(0,10).trim();
        return visitRepository.findAllVisitByDoctorIdForCurrentDay(nowOnlyDate, doctorId);
    }

    public Doctor findDoctorByEmail(String doctorEmail){
        return doctorRepository.findByEmail(doctorEmail);
    }
}
