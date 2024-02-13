package com.betaplan.uerdi.quotedash.controllers;

import com.betaplan.uerdi.quotedash.models.LoginUser;
import com.betaplan.uerdi.quotedash.models.Quote;
import com.betaplan.uerdi.quotedash.models.User;  // Import the correct User class
import com.betaplan.uerdi.quotedash.services.FileService;
import com.betaplan.uerdi.quotedash.services.QuoteService;
import com.betaplan.uerdi.quotedash.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID != null) {
            return "redirect:/dashboard";
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
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
        User user = userService.login(newLogin, result);

        if (result.hasFieldErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserID", user.getId());
        return "redirect:/dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(@ModelAttribute("newQuote") Quote newQuote,HttpSession session, Model model) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID == null) {
            return "redirect:/";
        }
        User loggedInUser = userService.findOneUser(loggedInUserID);
        model.addAttribute("user", loggedInUser);


        List<Quote> quote= quoteService.findAll();
        model.addAttribute("quote", quote);
        return "/dashboard";
    }

    @PostMapping("/dashboard/createQuote")
    public String createQuote(@Valid @ModelAttribute("newQuote") Quote newQuote, BindingResult result , HttpSession session, Model model) {

        if (result.hasErrors()) {
            User user = userService.findOneUser((Long) session.getAttribute("loggedInUserID"));
            model.addAttribute("user", user);
            return "/dashboard";
        } else {
            User user = userService.findOneUser((Long) session.getAttribute("loggedInUserID"));
            newQuote.setLead(user);
            quoteService.createQuote(newQuote);
            return "redirect:/dashboard";
        }
    }
    @GetMapping("/edit")
    public String editAccount(Model model, HttpSession session) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID == null) {
            return "redirect:/";
        }
        User user = userService.findOneUser(loggedInUserID);
        model.addAttribute("editedUser", user);

        return "/edit";
    }

    @PutMapping("/update")
    public String editAccount(@Valid @ModelAttribute("editedUser") User editedUser, BindingResult result,HttpSession session) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID != null) {
           return "redirect:/";
        }
        if (result.hasErrors()){
            return "/edit";
        } else {
            userService.updateUser(editedUser);
            return "redirect:/dashboard";
        }
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id, Model model, HttpSession session) {
        Long userID = (Long) session.getAttribute("userId");
        if (userID == null) {
            return "redirect:/";
        }
        Quote quote = quoteService.findQuoteById(id);
        model.addAttribute("quote", quote);
        model.addAttribute("user", userService.findOneUser((Long) session.getAttribute("userId")));
        return "/dashboard";
    }
    @GetMapping("/unlike/{id}")
    public String unlike(@PathVariable Long id, HttpSession session) {
        User user = userService.findOneUser((Long) session.getAttribute("userId"));
        Quote quote = quoteService.findQuoteById(id);

        for (int i = 0; i < quote.getLikers().size(); i++){
            if (user.getId() == quote.getLikers().get(i).getId()){
                quote.getLikers().remove(i);
                break;
            }

        }
        quoteService.createQuote(quote);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete")
    public String delete(Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null){
            return "redirect:/";
        }
        quoteService.deleteQuote(id);
        return "redirect:/dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}

