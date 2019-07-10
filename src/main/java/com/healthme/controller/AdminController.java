package com.healthme.controller;

import com.healthme.model.Doctor;
import com.healthme.model.Patient;
import com.healthme.model.Role;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.DoctorSpecializationRepository;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.PatientRepository;
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
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "admin/home";
    }

    @RequestMapping(value = "/approvals", method = RequestMethod.GET)
    public String getAccountApprovalView(Model model) {
        List<Patient> notEnabledPatients = patientRepository.getAllNotEnabled();
        model.addAttribute("notEnabledPatients", notEnabledPatients);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/approve/{id}", method = RequestMethod.GET)
    public String approvePatientAccount(@PathVariable Long id) {
        Patient patient = patientRepository.getOne(id);
        patient.setEnabled("true");
        patientRepository.save(patient);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/patient/discard/{id}", method = RequestMethod.GET)
    public String discardPatientAccount(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.getOne(id);
        patientRepository.delete(patient);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getDoctorListView(Model model) {
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        List<Doctor> doctors = doctorRepository.findAllDoctors(doctorRole);
        model.addAttribute("doctors", doctors);
        return "admin/doctorList";
    }

    @RequestMapping(value = "/remove/doctor/{id}", method = RequestMethod.GET)
    public String removeDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getOne(id);
        doctorRepository.delete(doctor);
        return "admin/doctorList";
    }

    @RequestMapping(value = "/edit/doctor/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.getOne(id);
        model.addAttribute("doctor", doctor);
        return "admin/editDoctor";
    }

    @RequestMapping(value = "/edit/doctor", method = RequestMethod.POST)
    public String editDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.GET)
    public String getAddDoctorView(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorSpecializationList",doctorSpecializationRepository.findAll());
        return "admin/addDoctor";
    }

    @RequestMapping(value = "/add/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        String password = doctor.getPassword();
        doctor.setPassword(passwordEncoder.encode(password));
        doctor.setEnabled("true");
        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        doctor.setRoles(Arrays.asList(doctorRole));
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

}
