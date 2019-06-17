package com.healthme.service;

import com.healthme.entity.Doctor;
import com.healthme.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctotServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctotServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);

    }

    @Override
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
