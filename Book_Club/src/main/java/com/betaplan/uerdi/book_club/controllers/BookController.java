package com.betaplan.uerdi.book_club.controllers;

import com.betaplan.uerdi.book_club.models.Book;
import com.betaplan.uerdi.book_club.models.LoginUser;
import com.betaplan.uerdi.book_club.models.User;
import com.betaplan.uerdi.book_club.services.BookService;
import com.betaplan.uerdi.book_club.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") LoginUser newLogin, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/home";
        }

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        User user = userService.register(newUser, result);

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }

        session.setAttribute("userId", user.getId());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }

        List<Book> allBooks = bookService.all();
        model.addAttribute("books", allBooks);

        User loggedInUser = userService.findById((Long) session.getAttribute("userId"));
        model.addAttribute("user", loggedInUser);

        return "home";
    }

    @GetMapping("/addPage")
    public String addPage(@ModelAttribute("book") Book book, Model model, HttpSession session) {
        User user = userService.findById((Long) session.getAttribute("userId"));
        model.addAttribute("user", user);

        return "addPage";
    }

    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "addPage";
        }

        User loggedInUser = userService.findById((Long) session.getAttribute("userId"));
        book.setUser(loggedInUser);

        bookService.create(book);

        return "redirect:/home";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(Model model,
                             @PathVariable("id") Long id,
                             HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/home";
        }

        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        User loggedInUser = userService.findById((Long) session.getAttribute("userId"));
        model.addAttribute("user", loggedInUser);

        return "book";
    }

    @GetMapping("/books/{id}/edit")
    public String getEditBook(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/home";
        }

        Book bookEdit = bookService.findById(id);
        model.addAttribute("bookEdit", bookEdit);

        return "editBook";
    }

    @PutMapping("/books/{id}/update")
    public String editBook(@Valid @ModelAttribute("bookEdit") Book bookEdit,
                           BindingResult result, Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User userLogged = userService.findById(userId);

        if (result.hasErrors()) {
            return "editBook";
        } else {
            bookService.update(bookEdit, userLogged);

            return "redirect:/home";
        }
    }

    @DeleteMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
        bookService.deleteBook(id);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
