package com.healthme.controller;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Prescription;
import com.healthme.model.entity.SickNote;
import com.healthme.model.entity.Visit;
import com.healthme.service.SickNoteService;
import com.healthme.service.doctor.DoctorService;
import com.healthme.service.prescription.PrescriptionService;
import com.healthme.service.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {


    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    private final SickNoteService sickNoteService;

    @Autowired
    public DoctorController(DoctorService doctorService,
                            PrescriptionService prescriptionService,
                            VisitService visitService,
                            SickNoteService sickNoteService) {
        this.doctorService = doctorService;
        this.prescriptionService = prescriptionService;
        this.visitService = visitService;
        this.sickNoteService = sickNoteService;
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

    @RequestMapping(value="/fillVisitFields/{visitId}", method = RequestMethod.GET)
    public String fillVisitFields(@PathVariable Long visitId, Model model){

        model.addAttribute("visit", visitService.findVisitById(visitId));
        return "doctor/fillVisitFields";
    }

    @RequestMapping(value="/fillVisitFields",method = RequestMethod.POST)
    public String saveChangedVisit(@Valid Visit visit, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "redirect:/doctor/fillVisitFields/"+visit.getId();
        }else{
            visitService.saveVisit(visit);
            return "redirect:/doctor/home";
        }
    }

    @RequestMapping(value="/addSickNote/{patientEmail}/{visitId}", method = RequestMethod.GET)
    public String addSickNote(Model model,
                              @PathVariable String patientEmail,
                              @PathVariable String visitId,
                              HttpServletResponse response){

        Cookie cookiePatientEmail = new Cookie("patientEmail",patientEmail);
        Cookie cookieVisitId = new Cookie("visitId", visitId);
        cookiePatientEmail.setPath("/doctor/addSickNote");
        cookieVisitId.setPath("/doctor/addSickNote");
        response.addCookie(cookiePatientEmail);
        response.addCookie(cookieVisitId);
        model.addAttribute("sickNote", new SickNote());

        return "doctor/addSickNote";
    }

    @RequestMapping(value = "/addSickNote", method = RequestMethod.POST)
    public String saveSickNote(@Valid SickNote sickNote, BindingResult bindingResult,HttpServletRequest request){
        Cookie patientEmail = WebUtils.getCookie(request, "patientEmail");
        Cookie visitId = WebUtils.getCookie(request,"visitId");

        if(bindingResult.hasErrors()) {

            return "redirect:/doctor/addSickNote/" + patientEmail.getValue()+"/"+visitId.getValue();

            }else{

                sickNoteService.saveSickNote(sickNote,request.getUserPrincipal(),patientEmail.getValue(),visitId.getValue());
                patientEmail.setMaxAge(0);
                visitId.setMaxAge(0);
                return "redirect:/doctor/home";
            }
        }


    @RequestMapping(value = "/myPatients",method = RequestMethod.GET)
    public String getAllPatientsByDoctorAndVisits(Model model, Principal principal){

        Long doctorId = doctorService.findDoctorByEmail(principal.getName()).getId();
        model.addAttribute("patients",doctorService.findAllPatientsByDoctorAndVisits(doctorId));
        return "/doctor/myPatients";

        }


    @RequestMapping(value="/patientHistory/{id}", method =RequestMethod.GET)
    public String showTreatmentHistory(Model model, @PathVariable Long id){

        model.addAttribute("visits",visitService.findVisitByPatient(id));
        return "doctor/treatmentHistory";
    }


    @ModelAttribute("codes")
    public List<String> codes() {
        List<String> codes = new ArrayList<>();
        codes.add("A");
        codes.add("B");
        codes.add("C");
        codes.add("D");
        codes.add("E");
        return codes;
    }

}
