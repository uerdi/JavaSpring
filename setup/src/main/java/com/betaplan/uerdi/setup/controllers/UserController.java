package com.betaplan.uerdi.setup.controllers;

import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// imports removed for brevity
@Controller
public class UserController {

    @RequestMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
}

