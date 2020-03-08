package ru.smith.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "series", schema = "library")
@ToString
@EqualsAndHashCode
public class Series implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @Getter @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @Getter @Setter
    private Publisher publisher;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private List<Book> books;

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public Series() {
        books = new ArrayList<>();
    }
}
