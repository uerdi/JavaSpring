package com.betaplan.uerdi.quotedash.services;

import com.betaplan.uerdi.quotedash.models.Quote;
import com.betaplan.uerdi.quotedash.models.User;
import com.betaplan.uerdi.quotedash.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }
    public Quote findById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }
    public Quote createQuote(Quote quote) {

        return quoteRepository.save(quote);
    }
    public Quote findQuoteById(Long id){
        Optional<Quote> optionalQuote = quoteRepository.findById(id);

        if (optionalQuote.isPresent()){
            return optionalQuote.get();
        } else {
            return null;
        }
    }
    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

}
