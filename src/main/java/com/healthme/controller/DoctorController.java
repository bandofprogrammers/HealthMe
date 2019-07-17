package com.healthme.controller;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Prescription;
import com.healthme.service.doctor.DoctorService;
import com.healthme.service.prescription.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/doctor")
public class DoctorController {


    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public DoctorController(DoctorService doctorService,
                            PrescriptionService prescriptionService) {
        this.doctorService = doctorService;
        this.prescriptionService = prescriptionService;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView(Model model, Principal principal){
        Doctor doctor = doctorService.findDoctorByEmail(principal.getName());
        model.addAttribute("visits",
            doctorService.findAllVisitByDoctorIdForCurrentDay(doctor.getId()));
    return "doctor/home";
    }

    @RequestMapping(value="/addPrescription/{patientEmail}", method = RequestMethod.GET)
    public String addPrescription(Model model, @PathVariable String patientEmail, HttpServletResponse response){

        Cookie patientEmailCookie = new Cookie("patientEmail",patientEmail);
        patientEmailCookie.setPath("/doctor/addPrescription");
        response.addCookie(patientEmailCookie);
        model.addAttribute("prescription", new Prescription());
        return "doctor/addPrescription";
    }

    @RequestMapping(value = "/addPrescription", method = RequestMethod.POST)
    public  String savePrescription(@Valid Prescription prescription,
                                    BindingResult bindingResult,
                                    Principal principal,
                                    HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "doctor/addPrescription";
        }else{
            Cookie patientEmail = WebUtils.getCookie(request,"patientEmail");
            String email = null;
            if (patientEmail != null) {
                email = patientEmail.getValue();
                patientEmail.setMaxAge(0);
            }
            prescriptionService.savePrescription(prescription,principal,email);
            return "redirect:/doctor/home";
        }
    }

}
