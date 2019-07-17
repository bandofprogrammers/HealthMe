package com.healthme.controller;

import com.healthme.model.entity.Doctor;
import com.healthme.repository.VisitRepository;
import com.healthme.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/doctor")
public class DoctorController {


    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService, VisitRepository visitRepository) {
        this.doctorService = doctorService;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView(Model model, Principal principal){
        Doctor doctor = doctorService.findDoctorByEmail(principal.getName());
        model.addAttribute("visits",
            doctorService.findAllVisitByDoctorIdForCurrentDay(doctor.getId()));
    return "doctor/home";
    }




}
