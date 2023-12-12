package com.betaplan.uerdi.book_api.repositories;

import com.betaplan.uerdi.book_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

