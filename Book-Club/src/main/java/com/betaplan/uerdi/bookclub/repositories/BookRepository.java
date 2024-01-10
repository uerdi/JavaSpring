package com.betaplan.uerdi.book_club.repositories;

import java.awt.print.Book;
import java.util.List;

import com.betaplan.uerdi.book-club.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
}

