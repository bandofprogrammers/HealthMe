package com.healthme.service.visit;

import com.healthme.model.entity.Visit;
import com.healthme.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public void saveVisit(Visit visit){
        visitRepository.save(visit);
    }

    public Optional<Visit> findVisitById(Long id){
        return visitRepository.findById(id);
    }
}
