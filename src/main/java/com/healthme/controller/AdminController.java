package com.healthme.controller;

import com.healthme.model.entity.Doctor;
import com.healthme.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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
    public String removeDoctor(@PathVariable Long id) {
        adminService.removeDoctorById(id);
        return "admin/doctorList";
    }

    @RequestMapping(value = "/edit/doctor/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", adminService.getDoctorById(id));
        return "admin/editDoctor";
    }

    @RequestMapping(value = "/edit/doctor", method = RequestMethod.POST)
    public String editDoctor(@ModelAttribute("doctor") Doctor doctor) {
        adminService.saveChangesInDoctorData(doctor);
        return "redirect:/admin/doctors";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.GET)
    public String getAddDoctorView(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "admin/addDoctor";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        adminService.addNewDoctor(doctor);
        return "redirect:/admin/doctors";
    }

}
