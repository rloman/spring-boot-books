package nl.yacht.books.api;

import nl.yacht.books.model.Author;
import nl.yacht.books.persistence.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        return ResponseEntity.ok(this.authorRepository.save(author));
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
