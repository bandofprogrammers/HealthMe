package com.healthme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/patient")
    public String getIndexView(){
        return "home/patient";
    }

    @RequestMapping("/")
    public String getLoggedIndexView(){
        return "home/home";
    }

    @RequestMapping("/admin")
    public String getAdminView(){
        return "home/admin";
    }


}
