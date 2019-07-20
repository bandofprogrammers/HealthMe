package com.healthme.service;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.SickNote;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.SickNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class SickNoteService {

    private final SickNoteRepository sickNoteRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public SickNoteService(SickNoteRepository sickNoteRepository,
                           PatientRepository patientRepository,
                           DoctorRepository doctorRepository) {
        this.sickNoteRepository = sickNoteRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void saveSickNote(SickNote sickNote, Principal principal, String patientEmail){
        LocalDate creationDate = LocalDate.now();
        Doctor doctor = doctorRepository.findByEmail(principal.getName());
        sickNote.setCreationDate(creationDate);
        sickNote.setDoctor(doctor);
        sickNote.setPatient(patientRepository.findByEmail(patientEmail));
        sickNoteRepository.save(sickNote);
    }
}
