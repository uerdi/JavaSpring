package com.betaplan.uerdi.book_club.repositories;

import java.util.List;

import com.betaplan.uerdi.book_club.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
}
