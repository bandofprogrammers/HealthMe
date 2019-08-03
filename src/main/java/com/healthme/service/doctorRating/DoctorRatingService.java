package com.healthme.service.doctorRating;

import com.healthme.model.entity.DoctorRating;
import com.healthme.model.entity.Visit;
import com.healthme.repository.DoctorRatingRepository;
import com.healthme.service.doctor.DoctorService;
import com.healthme.service.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorRatingService  {

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

    public void saveRating(DoctorRating doctorRating, String visitId, String doctorEmail){

        doctorRating.setStatus("disabled");
        doctorRating.setDoctor(doctorService.findDoctorByEmail(doctorEmail));
        Optional<Visit> visitOpt = visitService.findVisitById(Long.valueOf(visitId));
        Visit visit = new Visit();
        if(visitOpt.isPresent()){
            visit = visitOpt.get();

        }
        doctorRating.setVisit(visit);
        DoctorRating doctorRating1 = doctorRatingRepository.save(doctorRating);
        visit.setDoctorRating(doctorRating1);
        visitService.saveVisit(visit);

    }
}
