package com.betaplan.uerdi.loginregister.controllers;

import com.betaplan.uerdi.loginregister.models.LoginUser;
import com.betaplan.uerdi.loginregister.models.User;
import com.betaplan.uerdi.loginregister.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("new User") User newUser,
                        @ModelAttribute("new Login") LoginUser newLogin, HttpSession session){
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("newUser",new User());
        model.addAttribute("newLogin",new LoginUser());
        return "index";
      }

      @PostMapping("/register")
       public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
        userService.register(newUser, result);
        if (result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loggedInUserID", newUser.getId());
        return "redirect:/dashboard";

      }

      @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
        User user= userService.login(newLogin, result);

        if (result.hasFieldErrors()){
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserID", user.getId());
        return "redirect:/dashboard";

    }
    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session,Model model) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if(loggedInUserID == null){
            return "redirect:/";
        }
        User loggedInUser = userService.findOneUser(loggedInUserID);
        model.addAttribute("user",loggedInUser);

        return "dashboard";

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}



