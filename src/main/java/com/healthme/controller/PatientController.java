package com.healthme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @RequestMapping("/home")
    public String getHomeView() {
        return "home/patient";
    }
}
