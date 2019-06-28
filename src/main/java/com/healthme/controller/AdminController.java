package com.healthme.controller;

import com.healthme.model.User;
import com.healthme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/home")
    public String getHomeView() {
        return "admin/home";
    }

    @RequestMapping("/approvals")
    public String getAccountApprovalView(Model model) {
        List<User> notEnabledUsers = userRepository.getAllNotEnabled();
        model.addAttribute("notEnabledUsers", notEnabledUsers);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public String approvePatientAccount(@PathVariable Long id, Model model) {
        User patient = userRepository.getOne(id);
        patient.setEnabled("true");
        userRepository.save(patient);
        return "admin/userApprovalList";
    }

    @RequestMapping(value = "/discard/{id}", method = RequestMethod.GET)
    public String discardPatientAccount(@PathVariable Long id, Model model) {
        User patient = userRepository.getOne(id);
        userRepository.delete(patient);
        return "admin/userApprovalList";
    }
}
