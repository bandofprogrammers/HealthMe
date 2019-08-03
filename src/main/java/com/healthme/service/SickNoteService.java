package com.healthme.service;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.SickNote;
import com.healthme.model.entity.Visit;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.SickNoteRepository;
import com.healthme.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SickNoteService {

    private final SickNoteRepository sickNoteRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public SickNoteService(SickNoteRepository sickNoteRepository,
                           PatientRepository patientRepository,
                           DoctorRepository doctorRepository,
                           VisitRepository visitRepository) {
        this.sickNoteRepository = sickNoteRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
    }

    public void saveSickNote(SickNote sickNote, Principal principal, String patientEmail, String visitId){

        LocalDate creationDate = LocalDate.now();
        Doctor doctor = doctorRepository.findByEmail(principal.getName());
        Optional<Visit> visit = visitRepository.findById(Long.valueOf(visitId));
        sickNote.setCreationDate(creationDate);
        sickNote.setDoctor(doctor);
        sickNote.setPatient(patientRepository.findByEmail(patientEmail));
        Visit vis = new Visit();

        if(visit.isPresent()){
            vis = visit.get();
            sickNote.setVisit(vis);
        }
        sickNoteRepository.save(sickNote);
        vis.setSickNote(sickNote);
        visitRepository.save(vis);

    }
}
