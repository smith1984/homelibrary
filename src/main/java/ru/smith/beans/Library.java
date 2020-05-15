package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.smith.annotations.Entities;
import ru.smith.models.Author;
import ru.smith.models.Book;
import ru.smith.models.Story;
import ru.smith.services.EntityService;
import ru.smith.utils.StringCreate;

import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
@NoArgsConstructor
public class Library implements Serializable {

    @Inject
    @ru.smith.annotations.EntityService(Entities.BOOK)
    private EntityService bookService;

    @Getter
    @Setter
    private Book selectedBook;

    @Getter
    private List<Book> currentBookList;

    @PostConstruct
    public void setCurrentBookList() {
        this.currentBookList = (List<Book>) bookService.findAll();
    }

    public String getAllAuthorsForBook(Book book) {
        Set<Author> uniqueAuthors = new HashSet<>();
        for (Story story : book.getStories()) {
            uniqueAuthors.addAll(story.getAuthors());
        }
        return StringCreate.createStrAuthors(new ArrayList<>(uniqueAuthors), ", ");
    }
    public String getAllAuthorsForStory(Story story) {
        return StringCreate.createStrAuthors(story.getAuthors(), ", ");
    }

}
