package ru.smith.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "authors", schema = "library")
@ToString
@EqualsAndHashCode
public class Author implements Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(nullable = false)
    @Getter @Setter
    private String lastName;

    @Column
    @Getter @Setter
    private String firstName;

    @Column
    @Getter @Setter
    private String middleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "story_author", schema = "library", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "story_id"))
    @Getter @Setter
    private List<Story> stories;

    public void addStory(Story story){
        stories.add(story);
    }

    public void removeStory(Story story){
        stories.remove(story);
    }

    public Author() {
        stories = new ArrayList<>();
    }
}
