package com.healthme.service.prescription;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Patient;
import com.healthme.model.entity.Prescription;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public void savePrescription(Prescription prescription, Principal principal, String patientEmail){

        LocalDate now = LocalDate.now();
        String doctorEmail = principal.getName();
        Doctor doctor = doctorRepository.findByEmail(doctorEmail);
        Patient patient = patientRepository.findByEmail(patientEmail);

        prescription.setCreated(now);
        prescription.setExpiredDate(now.plusDays(30));
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescriptionRepository.save(prescription);
    }
}
