package com.betaplan.uerdi.savetravels.repositories;

import com.betaplan.uerdi.savetravels.models.Expenses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expenses,Long> {
    public List<Expenses>findAll();
}
