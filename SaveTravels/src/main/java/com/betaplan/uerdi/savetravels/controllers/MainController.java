package com.betaplan.uerdi.savetravels.controllers;

import com.betaplan.uerdi.savetravels.models.Expenses;
import com.betaplan.uerdi.savetravels.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String rootPath() {
        return "redirect:/expenses";
    }

    @GetMapping("/expenses")
    public String home(@ModelAttribute("expense") Expenses expense, Model model) {
        List<Expenses> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "index";
    }

    @PostMapping("/create/expense")
    public String newExpense(@Valid @ModelAttribute("expense") Expenses expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Expenses> expenses = expenseService.getAllExpenses();
            model.addAttribute("expenses", expenses);
            return "index";
        } else {
            expenseService.createExpense(expense);
            return "redirect:/expenses";
        }
    }

    @GetMapping("/expenses/update/{id}")
    public String editExpense(@PathVariable("id") Long id, Model model) {
        model.addAttribute("expense", expenseService.findExpense(id));
        return "edit";
    }

    @PutMapping("/expenses/edit/{id}")
    public String updateExpense(@PathVariable("id") Long id, @Valid @ModelAttribute("expense") Expenses expense, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/expenses/update/{id}";
        } else {
            expenseService.updateExpense(expense);
            return "redirect:/expenses";
        }
    }

    @RequestMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        Expenses expenses = expenseService.findExpense(id);
        expenseService.deleteExpense(expenses);
        return "redirect:/expenses";
    }

    @GetMapping("/details/{id}")
    public String expenseDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("expense", expenseService.findExpense(id));
        return "details";
    }
}

