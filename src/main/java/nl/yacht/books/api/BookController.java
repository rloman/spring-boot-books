package nl.yacht.books.api;

import nl.yacht.books.model.Book;
import nl.yacht.books.service.BookService;
import nl.yacht.books.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@Controller // error please use @RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    @PostMapping
    public Book create(@RequestBody Book toBeCreated) {
        return this.bookService.save(toBeCreated);
    }

    @GetMapping
    public Iterable<Book> list() {

        return this.bookService.findAll();

    }

    @GetMapping("team")
    public ResponseEntity<Void> pp() {
        this.personService.pp();

        return ResponseEntity.ok().build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findyById(@PathVariable("id") long id) {

        Optional<Book> optionalBook = this.bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book result = optionalBook.get();// only do get when you tested using isPresent()!!!!!!!!!!!!!
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable long id, @RequestBody Book in) {

        //first fetch the book with id: id
        Optional<Book> optionalBook = this.bookService.findById(id);

        // unwrap the optional
        if (optionalBook.isPresent()) {

            // get the book
            Book out = optionalBook.get();

            // set the properties from in to out
            out.setTitle(in.getTitle());
            out.setYearOfPublishing(in.getYearOfPublishing());

            // save out

            return ResponseEntity.ok(this.bookService.save(out));

            // return out
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> delete(@PathVariable long id) {

        Optional<Book> optionalBook = this.bookService.findById(id);

        if (optionalBook.isPresent()) {
            this.bookService.deleteById(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
