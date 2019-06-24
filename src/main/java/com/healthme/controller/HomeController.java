package com.healthme.controller;


import com.healthme.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHomeView(){
        return "home/home";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String getHomeView2(){
        MyUserDetailsService myUserDetailsService = new MyUserDetailsService();

        return String.valueOf(myUserDetailsService.loadUserByUsername("test@test.com"));}
}
