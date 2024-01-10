package com.betaplan.uerdi.book_club.services;

import com.betaplan.uerdi.book_club.models.User;  // Import the correct User class
import java.util.List;
import java.util.Optional;

import com.betaplan.uerdi.book_club.models.Book;
import com.betaplan.uerdi.book_club.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public Book findById(Long id) {
        Optional<Book> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public Book update(Book updatedBook, User user) {
        Optional<Book> optionalBook = repo.findById(updatedBook.getId());

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Check if the book belongs to the user before updating
            if (existingBook.getUser().getId().equals(user.getId())) {
                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setAuthor(updatedBook.getAuthor());
                existingBook.setThoughts(updatedBook.getThoughts());

                // Save the updated book
                return repo.save(existingBook);
            }
        }

        // If the book doesn't exist or doesn't belong to the user, return null or handle it accordingly
        return null;
    }

    public List<Book> all() {
        return repo.findAll();
    }

    public Book create(Book book) {
        return repo.save(book);
    }

    public void deleteBook(Long id) {
        repo.deleteById(id);
    }
}

