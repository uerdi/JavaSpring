package com.betaplan.uerdi.quotedash.controllers;

import com.betaplan.uerdi.quotedash.models.LoginUser;
import com.betaplan.uerdi.quotedash.models.Quote;
import com.betaplan.uerdi.quotedash.models.User;  // Import the correct User class
import com.betaplan.uerdi.quotedash.services.QuoteService;
import com.betaplan.uerdi.quotedash.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
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
    public String dashboard(HttpSession session, Model model) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID == null) {
            return "redirect:/";
        }
        User loggedInUser = userService.findOneUser(loggedInUserID);
        model.addAttribute("user", loggedInUser);
        List<Quote> quote = quoteService.findAll();
        model.addAttribute("quote", quote);
        return "dashboard";
    }

    @PostMapping("/dashboard/createQuote")
    public String createQuote(@Valid @ModelAttribute("quote") Quote quote, BindingResult result , HttpSession session, Model model) {

        if (result.hasErrors()) {
            User user = userService.findOneUser((Long) session.getAttribute("loggedInUserID"));
            model.addAttribute("user", user);
            return "/dashboard";
        } else {
            User user = userService.findOneUser((Long) session.getAttribute("loggedInUserID"));
            quote.setLead(user);
            quoteService.createQuote(quote);
            return "redirect:/dashboard";
        }
    }




    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}

