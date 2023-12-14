package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    @GetMapping("/books/{id}")
    public String showBook(@PathVariable Long id, Model model) {
        // Assuming you have a service to retrieve book information by ID
        Book book = bookService.findById(id);

        // Add book information to the model
        model.addAttribute("title", book.getTitle());
        model.addAttribute("description", book.getDescription());
        model.addAttribute("language", book.getLanguage());
        model.addAttribute("numberOfPages", book.getNumberOfPages());

        return "show";
    }
}
