package ru.smith.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "stories", schema = "library")
@ToString
@EqualsAndHashCode
public class Story implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @Getter @Setter
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "story_book", schema = "library", joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Getter @Setter
    private List<Book> books;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "story_author", schema = "library", joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Getter @Setter
    private List<Author> authors;

    public void addAuthor(Author author){
        authors.add(author);
    }

    public void removeAuthor(Author author){
        authors.remove(author);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public Story() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
    }
}
