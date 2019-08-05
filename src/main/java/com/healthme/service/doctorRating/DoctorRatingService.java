package com.healthme.service.doctorRating;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.DoctorRating;
import com.healthme.model.entity.Visit;
import com.healthme.repository.DoctorRatingRepository;
import com.healthme.service.doctor.DoctorService;
import com.healthme.service.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorRatingService {

    private final DoctorRatingRepository doctorRatingRepository;
    private final VisitService visitService;
    private final DoctorService doctorService;


    @Autowired
    public DoctorRatingService(DoctorRatingRepository doctorRatingRepository,
                               VisitService visitService,
                               DoctorService doctorService) {
        this.doctorRatingRepository = doctorRatingRepository;
        this.visitService = visitService;
        this.doctorService = doctorService;
    }

    public void saveRating(DoctorRating doctorRating, String doctorEmail,String visitId) {

        doctorRating.setStatus("disabled");
        doctorRating.setDoctor(doctorService.findDoctorByEmail(doctorEmail));
        Visit visit = visitService.findVisitById(Long.valueOf(visitId));
        doctorRating.setVisit(visit);
        DoctorRating rating = doctorRatingRepository.save(doctorRating);
        visit.setDoctorRating(doctorRatingRepository.getOne(rating.getId()));
        visitService.saveVisit(visit);
        Doctor doctor = doctorService.findDoctorByEmail(doctorEmail);
        doctor.setCurrentRating(calculateAvgRatingByDoctor(doctor.getId()));
        doctorService.save(doctor);

    }

    public double calculateAvgRatingByDoctor(Long doctorId){
        return doctorRatingRepository.findAVGRatingByDoctor(doctorId);
    }

}