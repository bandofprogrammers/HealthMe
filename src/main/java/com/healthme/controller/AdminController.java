package com.healthme.controller;

import com.healthme.model.Role;
import com.healthme.model.User;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "admin/home";
    }

    @RequestMapping(value = "/approvals", method = RequestMethod.GET)
    public String getAccountApprovalView(Model model) {
        List<User> notEnabledUsers = userRepository.getAllNotEnabled();
        model.addAttribute("notEnabledUsers", notEnabledUsers);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/approve/{id}", method = RequestMethod.GET)
    public String approvePatientAccount(@PathVariable Long id) {
        User patient = userRepository.getOne(id);
        patient.setEnabled("true");
        userRepository.save(patient);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/discard/{id}", method = RequestMethod.GET)
    public String discardPatientAccount(@PathVariable Long id, Model model) {
        User patient = userRepository.getOne(id);
        userRepository.delete(patient);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getDoctorListView(Model model) {
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        List<User> doctors = userRepository.findAllDoctors(doctorRole);
        model.addAttribute("doctors", doctors);
        return "admin/doctorList";
    }

    @RequestMapping(value = "/remove/doctor/{id}", method = RequestMethod.GET)
    public String removeDoctor(@PathVariable Long id) {
        User doctor = userRepository.getOne(id);
        userRepository.delete(doctor);
        return "admin/doctorList";
    }

    @RequestMapping(value = "/edit/doctor/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable Long id, Model model) {
        User doctor = userRepository.getOne(id);
        model.addAttribute("doctor", doctor);
        return "admin/editDoctor";
    }

    @RequestMapping(value = "/edit/doctor", method = RequestMethod.POST)
    public String editDoctor(@ModelAttribute("doctor") User doctor) {
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        userRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.GET)
    public String getAddDoctorView(Model model) {
        User doctor = new User();
        model.addAttribute("doctor", doctor);
        return "admin/addDoctor";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") User doctor) {
        String password = doctor.getPassword();
        doctor.setPassword(passwordEncoder.encode(password));
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        userRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

}
