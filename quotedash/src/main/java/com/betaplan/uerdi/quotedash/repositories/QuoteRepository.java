package com.betaplan.uerdi.quotedash.repositories;

import com.betaplan.uerdi.quotedash.models.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    List<Quote> findAll();
}
