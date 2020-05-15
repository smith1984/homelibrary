package ru.smith.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "books", schema = "library")
@ToString
@EqualsAndHashCode
public class Book implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private int pages;

    @Column(name = "year_publishing")
    @Getter @Setter
    private int yearPublishing;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @Getter @Setter
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @Getter @Setter
    private Series series;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "story_book", schema = "library", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "story_id"))
    @Getter @Setter
    private List<Story> stories;

    public void addStory(Story story) {
        stories.add(story);
    }

    public Book() {
        stories = new ArrayList<>();
    }
}
