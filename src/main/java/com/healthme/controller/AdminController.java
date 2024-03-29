package com.healthme.controller;


import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.repository.DoctorSpecializationRepository;
import com.healthme.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "admin/home";
    }

    @RequestMapping(value = "/approvals", method = RequestMethod.GET)
    public String getAccountApprovalView(Model model) {
        model.addAttribute("notEnabledPatients", adminService.getAllNotEnabledPatients());
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/approve/{id}", method = RequestMethod.GET)
    public String approvePatientAccount(@PathVariable Long id) {
        adminService.approvePatientAccountById(id);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/discard/{id}", method = RequestMethod.GET)
    public String discardPatientAccount(@PathVariable Long id) {
        adminService.removePatientById(id);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getDoctorListView(Model model) {
        model.addAttribute("doctors", adminService.findAllDoctors());
        return "admin/doctorList";
    }

    @RequestMapping(value = "/remove/doctor/{id}", method = RequestMethod.GET)
    public String removeDoctor(@PathVariable Long id, Model model) {
        adminService.removeDoctorById(id);
        model.addAttribute("doctors", adminService.findAllDoctors());
        return "admin/doctorList";
    }

    @RequestMapping(value = "/edit/doctor/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", adminService.getDoctorById(id));
        model.addAttribute("doctorSpecializationList", doctorSpecializationRepository.findAll());
        return "admin/editDoctor";
    }

    @RequestMapping(value = "/edit/doctor", method = RequestMethod.POST)
    public String editDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editDoctor";
        }
        adminService.saveChangesInDoctorData(doctor);
        return "redirect:/admin/doctors";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.GET)
    public String getAddDoctorView(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "admin/addDoctor";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addDoctor";
        }
        adminService.addNewDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    @RequestMapping(value = "/resetpassword/doctor/{id}", method = RequestMethod.GET)
    public String resetDoctorPassword(@PathVariable Long id, Model model) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        model.addAttribute("doctor", doctor);
        return "admin/resetDoctorPassword";
    }

    @RequestMapping(value = "/resetpassword/doctor", method = RequestMethod.POST)
    public String resetDoctorPassword(@ModelAttribute("doctor") Doctor doctor, Model model) {
        adminService.resetDoctorPassword(doctor);
        model.addAttribute("doctors", adminService.findAllDoctors());
        return "admin/doctorList";
    }

    @ModelAttribute("doctorSpecializationList")
    public List<DoctorSpecialization> specializations(){
        return doctorSpecializationRepository.findAll();
    }

    @ModelAttribute("genders")
    public List<String> genders() {
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders;
    }
}
