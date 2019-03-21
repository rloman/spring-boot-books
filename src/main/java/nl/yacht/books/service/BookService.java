package nl.yacht.books.service;


import nl.yacht.books.model.Book;
import nl.yacht.books.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;


    public Optional<Book> findById(Long aLong) {
        return bookRepository.findById(aLong);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }


    @Transactional
    public Book save(Book b) {
        return this.bookRepository.save(b);
    }
}
