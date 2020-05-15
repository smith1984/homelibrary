package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.smith.annotations.Entities;
import ru.smith.models.Book;
import ru.smith.models.Publisher;
import ru.smith.models.Series;
import ru.smith.models.Story;
import ru.smith.services.EntityService;
import ru.smith.utils.StringCreate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ViewScoped
@NoArgsConstructor
public class AddBook implements Serializable {

    @Getter
    @Setter
    private String bookTitle;

    @Getter
    @Setter
    private Integer countPages;

    @Getter
    @Setter
    private Integer yearPublishing;

    @Getter @Setter
    private Publisher publisher;

    private Series serie;

    @Getter
    @Setter
    private Set<Story> storyInBook = new HashSet<>();

    @Inject
    @ru.smith.annotations.EntityService(Entities.BOOK)
    private EntityService bookService;

    public String addBook() {
        Book book = new Book();
        book.setName(bookTitle);
        book.setPages(countPages);
        book.setYearPublishing(yearPublishing);
        if (serie != null)
            serie.addBook(book);
        storyInBook.forEach(book::addStory);
        book.setPublisher(publisher);
        bookService.saveEntity(book);
        return "/library?faces-redirect=true";
    }

    public void addStoryToList(Story story) {
        storyInBook.add(story);
    }

    public void addPublisherForBook(Publisher pub) {
        publisher = pub;
    }

    public void addSerieForBook(Series ser) {
        serie = ser;
    }

    public String getAllStoryForBook() {
        return StringCreate.createStrStories(storyInBook.stream().collect(Collectors.toList()), ", ");
    }
}
