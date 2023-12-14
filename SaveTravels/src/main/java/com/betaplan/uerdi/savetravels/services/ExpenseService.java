package com.betaplan.uerdi.savetravels.services;

import com.betaplan.uerdi.savetravels.models.Expenses;
import com.betaplan.uerdi.savetravels.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expenses> getAllExpenses() {

        return expenseRepository.findAll();
    }
    public void createExpense(Expenses expenses) {
        expenseRepository.save(expenses);
    }
    public Expenses findExpense(Long id) {
        Optional<Expenses> optionalExpenses = expenseRepository.findById(id);
        if(optionalExpenses.isPresent()) {
            return optionalExpenses.get();
        }
        else {
            return null;
        }
    }
    public Expenses updateExpense(Expenses expense){
        return expenseRepository.save(expense);
    }
    public void deleteExpense(Expenses expenses) {
        expenseRepository.delete(expenses);
    }
}
