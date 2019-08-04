package com.healthme.service.doctorSpecialization;

import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.repository.DoctorSpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSpecializationService {

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    public List<DoctorSpecialization> findAll() {
        return doctorSpecializationRepository.findAll();
    }

    public DoctorSpecialization findByName(String name){
        return doctorSpecializationRepository.findByName(name);
    }
}
