package nl.yacht.books.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private LocalDate dateOfBirth;


    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();


    public void addBook(Book b) {
        this.books.add(b);
        b.setAuthor(this);
    }

    public Set<Book> getBooks() {
        return books;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
