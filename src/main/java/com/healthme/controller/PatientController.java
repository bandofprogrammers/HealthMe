package com.healthme.controller;

import com.healthme.model.UserDto;
import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.model.entity.Patient;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.DoctorSpecializationRepository;
import com.healthme.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "patient/home";
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getDoctorsView(Model model) {
        model.addAttribute("specializations", doctorSpecializationRepository.findAll());
        DoctorSpecialization internist = doctorSpecializationRepository.findByName("Internist");
        model.addAttribute("internists", doctorRepository.findAllBySpecialization(internist));
        return "patient/doctors";
    }

    @RequestMapping(value = "/specializations/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getDoctorsView(@PathVariable Long id) {
        return patientService.getDoctorListBySpecializationId(id).toString();
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    public String getDoctorScheduleView(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorRepository.getOne(id));
        return "patient/doctorSchedule";
    }

    @RequestMapping(value = "/prescriptions", method = RequestMethod.GET)
    public String getPrescriptionsView(Model model) {
        return "patient/prescriptions";
    }

    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    public String getVisitsView(Model model) {
        return "patient/visits";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterView(Model model) {
        model.addAttribute("patient", new UserDto());
        return "patient/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createPatientAccount(@ModelAttribute("patient") @Valid UserDto accountDto, BindingResult result) {

        Patient registered = new Patient();

        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto);
        }

        if (registered == null) {
            result.rejectValue("email", "email.duplicated", "Email already exists in the database");
        }

        if (result.hasErrors()) {
            return new ModelAndView("patient/register", "patient", accountDto);
        } else {
            return new ModelAndView("patient/registered", "patient", accountDto);
        }
    }

    private Patient createUserAccount(UserDto accountDto) {
        Patient registered = null;
        try {
            registered = patientService.registerNewPatientAccount(accountDto);
        } catch (NullPointerException e) {
            return null;
        }
        return registered;
    }

    @ModelAttribute("genders")
    public List<String> genders() {
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders;
    }
}
