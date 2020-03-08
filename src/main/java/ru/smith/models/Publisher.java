package ru.smith.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "publishers", schema = "library")
@ToString
@EqualsAndHashCode
public class Publisher implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(nullable = false)
    @Getter @Setter
    private String name;

    @Column(nullable = false)
    @Getter @Setter
    private  String city;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private List<Series> series;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private List<Book> books;

    public void addBook(Book book){
        book.setPublisher(this);
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public void addSeries(Series serie){
        serie.setPublisher(this);
        series.add(serie);
    }

    public void removeSeries(Series serie){
        series.remove(serie);
    }

    public Publisher() {
        books = new ArrayList<>();
        series = new ArrayList<>();
    }
}
