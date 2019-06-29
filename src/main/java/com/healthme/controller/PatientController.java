package com.healthme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeView() {
        return "patient/home";
    }
}
