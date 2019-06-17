package com.healthme.service;

import com.healthme.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {

    Optional<Doctor> findById(Long id);
    List<Doctor> findAll();
    void delete(Doctor doctor);
    void save(Doctor doctor);

}
