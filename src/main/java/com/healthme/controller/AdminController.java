package com.healthme.controller;

import com.healthme.model.User;
import com.healthme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String getAccountApprovalView(Model model){
        List<User> notEnabledUsers = userRepository.getAllNotEnabled();
        model.addAttribute("notEnabledUsers", notEnabledUsers);
        return "admin/userApprovalList";
    }
}
