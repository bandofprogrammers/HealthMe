package com.healthme.controller;

import com.healthme.model.UserDto;
import com.healthme.model.entity.DoctorRating;
import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.model.entity.Patient;
import com.healthme.model.entity.doctorsCalendar.WorkHour;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.DoctorSpecializationRepository;
import com.healthme.service.doctor.DoctorService;
import com.healthme.service.doctorRating.DoctorRatingService;
import com.healthme.repository.WorkHourRepository;
import com.healthme.service.patient.PatientService;
import com.healthme.service.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
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

    @Autowired

    private VisitService visitService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRatingService doctorRatingService;

    private WorkHourRepository workHourRepository;

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
    public String getDoctorSpecializations(@PathVariable Long id) {
        return patientService.getDoctorListBySpecializationId(id).toString();
    }


    @RequestMapping(value = "/availableterms/{doctorId}/{date}", method = RequestMethod.GET)
    public String getAvailableTerms(@PathVariable Long doctorId, @PathVariable String date, Model model) {
        List<WorkHour> workHours = workHourRepository.getAvailableTermsByDoctorIdAndDate(doctorId, date);
        model.addAttribute("hours", workHours);
        return "patient/testHours";
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
    public String getVisitsView(Model model, Principal principal) {
        Patient patient = patientService.findOneByEmail(principal.getName());
        model.addAttribute("visits", visitService.findVisitByPatient(patient.getId()));
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

    @RequestMapping(value="/rateDoctor/{doctorEmail}", method = RequestMethod.GET)
    public String rateDoctor(@PathVariable String doctorEmail,
                             Model model,
                             HttpServletResponse response){

        model.addAttribute("doctorRating", new DoctorRating());
        Cookie doctorEmailCookie = new Cookie("doctorEmail",doctorEmail);
        doctorEmailCookie.setPath("/patient/rateDoctor");
        response.addCookie(doctorEmailCookie);

        return "/patient/rateDoctor";
    }

    @RequestMapping(value="/rateDoctor", method = RequestMethod.POST)
    public String saveRating(@Valid DoctorRating doctorRating,
                             BindingResult bindingResult,
                             HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "redirect:/patient/rateDoctor"+ doctorRating.getId();
        }
        else{
            Cookie doctorEmailCookie = WebUtils.getCookie(request, "doctorEmail");
            doctorRating.setDoctor(doctorService.findDoctorByEmail(doctorEmailCookie.getValue()));
            doctorEmailCookie.setMaxAge(0);
            doctorRatingService.saveRating(doctorRating);
            return "redirect:/patient/visits";
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

    @ModelAttribute("rate")
    public List<Integer> rate() {
        List<Integer> rate = new ArrayList<>();

        for(int i = 0; i<11; i++){
            rate.add(i);
        }
        return rate;
    }
}
