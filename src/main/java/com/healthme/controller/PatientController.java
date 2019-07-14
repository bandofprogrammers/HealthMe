package com.healthme.controller;

import com.healthme.model.entity.Patient;
import com.healthme.model.UserDto;
import com.healthme.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "patient/home";
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
    public List<String> genders(){
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders;
    }
}
