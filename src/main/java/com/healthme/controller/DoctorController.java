package com.healthme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @RequestMapping("/home")
    public String getHomeView(){
        return "doctor/home";
    }
}
