package com.betaplan.provimi.provimi.controllers;

import com.betaplan.provimi.provimi.models.HouseHunter;
import com.betaplan.provimi.provimi.models.LoginUser;
import com.betaplan.provimi.provimi.models.User;
import com.betaplan.provimi.provimi.services.HouseHunterService;
import com.betaplan.provimi.provimi.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HouseHunterService hunterService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") LoginUser newLogin, HttpSession session) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID != null) {
            return "redirect:/home";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
        userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loggedInUserID", newUser.getId());
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
        User user = userService.login(newLogin, result);

        if (result.hasFieldErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserID", user.getId());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null){
            return "redirect:/";
        }
        User user = userService.findOneUser(userId);
        model.addAttribute("hunters", hunterService.all());
        model.addAttribute("user", user);

        return "home";
    }

    @GetMapping("/listings/new")
    public String newListing(@ModelAttribute("hunters") HouseHunter hunter, Model model, HttpSession session){
        User user = userService.findOneUser((Long) session.getAttribute("userId"));
        model.addAttribute("user", user);
        return "listings";
    }
    @PostMapping("/new")
    public String createListing(@Valid @ModelAttribute("hunters") HouseHunter hunter, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()){
            User user = userService.findOneUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            return "listings";
        } else {
            User user = userService.findOneUser((Long) session.getAttribute("userId"));
            hunter.setUser(user);
            hunterService.create(hunter);
            return "redirect:/home";
        }
    }
    @GetMapping("/listings/{id}")
    public String addressDetail(Model model, @PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/home";
        }
        HouseHunter houseHunter= hunterService.findById(id);
        model.addAttribute("hunters", houseHunter);
        model.addAttribute("user", userService.findOneUser((Long) session.getAttribute("userId")));

        return "details";
    }

    @GetMapping("/househunters/{id}/edit")
    public String getEditHouseHunter(@PathVariable("id")Long id, Model model, HttpSession session){
        if (session.getAttribute("userId") == null){
            return "redirect:/home";
        }
        HouseHunter houseHunterEdit= hunterService.findById(id);
        model.addAttribute("hunterEdit",houseHunterEdit);
        return "edit";
    }

    @PutMapping("househunters/{id}/update")
    public String editHouseHunter(@Valid @ModelAttribute("hunterEdit") HouseHunter houseHunter,
                                  BindingResult result, Model model,@PathVariable("id") Long id,
                                  HttpSession session){


        if (result.hasErrors()) {
            User user = userService.findOneUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            return "editHouseHunter";
        } else {
           User user = userService.findOneUser((Long) session.getAttribute("userId"));
           houseHunter.setUser(user);
           hunterService.update(houseHunter);
        }
        return "redirect:/home";
    }

    @DeleteMapping("/househunters/{id}/delete")
    public String deleteHouseHunter(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("userId") == null){
            return "redirect:/";
        }

        HouseHunter hunter = hunterService.findById(id);
        hunterService.deleteHouseHunter(hunter);
        return "redirect:/home";
    }



    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
