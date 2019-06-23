package com.healthme.controller;

import com.healthme.entity.Patient;
import com.healthme.model.UserDto;
import com.healthme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterView(Model model) {
        model.addAttribute("patient", new UserDto());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("patient") @Valid UserDto accountDto, BindingResult result) {

        Patient registered = new Patient();

        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }

        if (registered == null) {
            result.rejectValue("email", "email.duplicated", "Email already exists in the database");
        }

        if (result.hasErrors()) {
            return new ModelAndView("user/register", "patient", accountDto);
        } else {
            return new ModelAndView("user/registered", "patient", accountDto);
        }
    }

    private Patient createUserAccount(UserDto accountDto, BindingResult result) {
        Patient registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (NullPointerException e) {
            return null;
        }
        return registered;
    }
}
