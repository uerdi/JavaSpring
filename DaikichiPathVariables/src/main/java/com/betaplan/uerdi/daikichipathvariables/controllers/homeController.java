package com.betaplan.uerdi.daikichipathvariables.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daikichi")
public class homeController {
    @GetMapping("/{name}/{town}")
    public String name(@PathVariable String name, @PathVariable String town)
    {
        return String.format("Congratulations! You will soon %s to %s", name, town) ;
    }
    @GetMapping("/lotto")
    public String name(@PathVariable int number) {
        if (number%2 == 0){
            return String.format("You will take a grand journey in the near future");
        } else {
            return String.format("You have enjoyed the fruits of your labor but now is a great time to spend time with family and friends");
        }

    }

}
