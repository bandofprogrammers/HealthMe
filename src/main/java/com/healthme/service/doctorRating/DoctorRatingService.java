package com.healthme.service.doctorRating;

import com.healthme.model.entity.DoctorRating;
import com.healthme.repository.DoctorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorRatingService  {

    private final DoctorRatingRepository doctorRatingRepository;

    @Autowired
    public DoctorRatingService(DoctorRatingRepository doctorRatingRepository) {
        this.doctorRatingRepository = doctorRatingRepository;
    }

    public void saveRating(DoctorRating doctorRating){
        doctorRatingRepository.save(doctorRating);
    }
}
