package com.betaplan.uerdi.book_club.controllers;

import com.betaplan.uerdi.book-club.models.LoginUser;
import com.betaplan.uerdi.book-club.models.User;  // Import the correct User class
import com.betaplan.uerdi.book-club.services.BookService;
import com.betaplan.uerdi.book-club.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@Controller
public class BookController {

    @Autowired
    private UserService users;
    @Autowired
    private BookService books;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") LoginUser newLogin, HttpSession session) {
        if(session.getAttribute("userId") != null) {
            return "redirect:/home";
        }

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        com.betaplan.uerdi.book-club.models.User user = users.register(newUser, result);

        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        session.setAttribute("userId", user.getId());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }

        model.addAttribute("books", books.all());
        model.addAttribute("user", users.findById((Long)session.getAttribute("userId")));
        return "home.jsp";
    }

    @GetMapping("/addPage")
    public String addPage(@ModelAttribute("book") Book book, Model model, HttpSession session) {

        User user = users.findById((Long)session.getAttribute("userId"));
        model.addAttribute("user", user);

        return "addPage.jsp";
    }

    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "addPage.jsp";
        }

        // Assuming you have a proper create method in your BookService
        // and that it sets the user appropriately
        books.create(book);

        return "redirect:/home";
    }



    @GetMapping("/books/{id}")
    public String bookDetail(Model model,
                             @PathVariable("id") Long id,
                             HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/home";
        }
        Book book = books.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("user", users.findById((Long)session.getAttribute("userId")));
        return "book.jsp";
    }

    @GetMapping("/books/{id}/edit")
    public String getEditBook(@PathVariable("id") Long id, Model model,HttpSession session){
        if(session.getAttribute("userId") == null) {
            return "redirect:/home";
        }
        Book bookEdit = books.findById(id);
        model.addAttribute("bookEdit",bookEdit);
        return "editBook.jsp";
    }

    @PutMapping("/books/{id}/update")
    public String editBook(@Valid @ModelAttribute("bookEdit") Book bookEdit,
                           BindingResult result, Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User userLogged = users.findById(userId);

        if (result.hasErrors()) {
            return "editBook.jsp";
        } else {
            // Assuming you have a proper update method in your BookService
            // and that it sets the user appropriately
            books.update(bookEdit, userLogged);

            return "redirect:/home";
        }
    }

    @DeleteMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
        books.deleteBook(id);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}