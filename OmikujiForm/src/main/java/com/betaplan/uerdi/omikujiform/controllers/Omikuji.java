package com.betaplan.uerdi.omikujiform.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Omikuji {

    @GetMapping("/")
    public String index(){
        return "redirect:/omikuji";
    }
    @GetMapping("/omikuji")
    public String Omikuji()
    {
        return "omikuji";
    }
    @GetMapping("/omikuji/show")
    public String show(HttpSession session, Model model){
        String result = (String) session.getAttribute("resultOmikuji");
        model.addAttribute("result", result);

        return "show";
    }
    @PostMapping("/submitForm")
    public String process(
            @RequestParam("number") int number,
            @RequestParam("city") String city,
            @RequestParam("person") String person,
            @RequestParam("hobby") String hobby,
            @RequestParam("livingThing") String livingThing,
            @RequestParam("message") String message,
            HttpSession session){
        String resultOmikuji = String.format(
                "In %s years you will live in %s as your, %s. " +
                        "The next time you see a %s, you will have " +
                        "good luck. Also %s", number, city, person, hobby, livingThing, message);
        session.setAttribute("resultOmikuji", resultOmikuji);
        return "redirect:/omikuji/show";

    }
}
