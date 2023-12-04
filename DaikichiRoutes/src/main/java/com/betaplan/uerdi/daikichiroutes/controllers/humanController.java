package com.betaplan.uerdi.daikichiroutes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daikichi")
public class humanController {

    @GetMapping("/")
    public String welcome(){
        return "Welcome!";
    }
    @GetMapping("/today")
    public String today(){
        return "Today you will find luck in all your endeavors!";
    }
    @GetMapping("/tomorrow")
    public String tomorrow()
    {
        return "Tomorrow, an opportunity will arise, so be sure to be open to new ideas!";
    }

}