package com.betaplan.uerdi.book_api.services;

import com.betaplan.uerdi.book_api.models.Book;
import com.betaplan.uerdi.book_api.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public Book updateBook(Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(updatedBook.getId());

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setLanguage(updatedBook.getLanguage());
            existingBook.setNumberOfPages(updatedBook.getNumberOfPages());

            return bookRepository.save(existingBook);
        } else {

            return null;
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
