package com.betaplan.uerdi.dojosandninjas.controllers;

import com.betaplan.uerdi.dojosandninjas.models.Dojo;
import com.betaplan.uerdi.dojosandninjas.models.Ninja;
import com.betaplan.uerdi.dojosandninjas.services.DojoService;
import com.betaplan.uerdi.dojosandninjas.services.NinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private DojoService dojoService;

    @Autowired
    private NinjaService ninjaService;

    @GetMapping("/")
    public String rootPath() {
        return "redirect:/dojosandninjas";
    }

    @GetMapping("/dojos/new")
    public String newDojoForm(@ModelAttribute("dojo") Dojo dojo) {
        return "newDojoForm.jsp";
    }

    @GetMapping("/ninjas/new")
    public String newNinjaForm(@ModelAttribute("ninja") Ninja ninja, Model model) {
        model.addAttribute("dojos", dojoService.all());
        return "newNinjaForm.jsp";
    }

    @GetMapping("/dojos/{id}")
    public String showDojo(@PathVariable("id") Long id, Model model) {
        Dojo dojo = dojoService.find(id);
        model.addAttribute("dojo", dojo);
        return "showDojo.jsp";
    }

    @PostMapping("/dojos")
    public String createDojo(@ModelAttribute("dojo") Dojo dojo) {
        Dojo newDojo = dojoService.create(dojo);
        return String.format("redirect:/dojos/%s", newDojo.getId());
    }

    @PostMapping("/ninjas")
    public String createNinja(@ModelAttribute("ninja") Ninja ninja) {
        ninja = ninjaService.create(ninja);
        return "redirect:/dojos/" + ninja.getDojo().getId();
    }
}

