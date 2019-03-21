package nl.yacht.books.api;

import nl.yacht.books.model.Author;
import nl.yacht.books.model.Book;
import nl.yacht.books.persistence.AuthorRepository;
import nl.yacht.books.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        return ResponseEntity.ok(this.authorRepository.save(author));
    }

    @PostMapping("{authorId}")
    public ResponseEntity<Author> createBookForAuthor(@PathVariable("authorId") long id, @RequestBody Book book) {

        // move this to a service later (on your time or Ray in his time)
//        Book newBook = this.bookRepository.save(book);

        // /find the author

        Optional<Author> optionalAuthor =this.authorRepository.findById(id);
        if(optionalAuthor.isPresent()) {
            Author out = optionalAuthor.get();
            out.addBook(book);

            this.authorRepository.save(out);
            this.bookRepository.save(book);

            return ResponseEntity.ok(out);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<Iterable<Author>> findAll() {
        return ResponseEntity.ok(this.authorRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable long id) {
        Optional<Author> optionalAuthor = this.authorRepository.findById(id);

        if(optionalAuthor.isPresent()) {
            return ResponseEntity.ok(optionalAuthor.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
