package ru.smith.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.smith.annotations.Entities;
import ru.smith.models.Author;
import ru.smith.models.Story;
import ru.smith.services.EntityService;
import ru.smith.utils.StringCreate;

import javax.annotation.PostConstruct;
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
public class AddStory implements Serializable {

    @Getter @Setter
    private String nameStory;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String middleName;

    @Getter @Setter
    private Set<Author> authorsStory = new HashSet<>();

    @Inject
    @ru.smith.annotations.EntityService(Entities.STORY)
    private EntityService storyService;

    @Inject
    @ru.smith.annotations.EntityService(Entities.AUTHOR)
    private EntityService authorService;

    public List<String> completeStoryName(String query) {
        return ((List<Story>) storyService.findAll()).
                stream().filter(s -> s.getName().toLowerCase().contains(query.toLowerCase())).
                map(Story::getName).collect(Collectors.toList());
    }

    public List<String> completeLastName(String query) {
        return ((List<Author>) authorService.findAll()).stream().filter(a -> a.getLastName().toLowerCase().contains(query.toLowerCase())).
                map(Author::getLastName).collect(Collectors.toList());
    }

    public List<String> completeFirstName(String query) {
        return ((List<Author>) authorService.findAll()).stream().filter(a -> a.getLastName().toLowerCase().equals(lastName.toLowerCase())
                && a.getFirstName().toLowerCase().contains(query.toLowerCase())).
                map(Author::getFirstName).collect(Collectors.toList());
    }

    public List<String> completeMiddleName(String query) {
        return ((List<Author>) authorService.findAll()).stream().filter(a -> a.getLastName().toLowerCase().equals(lastName.toLowerCase())
                && a.getFirstName().toLowerCase().equals(firstName.toLowerCase())
                && a.getMiddleName().toLowerCase().contains(query.toLowerCase())).
                map(Author::getMiddleName).collect(Collectors.toList());
    }

    public void addAnotherAuthor() {
        var authors = ((List<Author>) authorService.findAll()).stream().filter(a -> a.getLastName().toLowerCase().equals(lastName.toLowerCase())
                && a.getFirstName().toLowerCase().equals(firstName.toLowerCase())
                && a.getMiddleName().toLowerCase().equals(middleName.toLowerCase()))
                .collect(Collectors.toList());

        if (!authors.isEmpty())
            authorsStory.add(authors.get(0));
        else {
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setMiddleName(middleName);
            authorsStory.add(author);
        }
        lastName = null;
        firstName = null;
        middleName = null;
    }

    public String getAllAuthorsForStory() {
        return StringCreate.createStrAuthors(authorsStory.stream().collect(Collectors.toList()), ", ");
    }

    public Story getStory(){
        addAnotherAuthor();
        var equalsNameStory = ((List<Story>) storyService.findAll()).
                stream().filter(story -> story.getName().toLowerCase().equals(nameStory) &&
                story.getAuthors().containsAll(authorsStory) &&
                authorsStory.containsAll(story.getAuthors())).collect(Collectors.toList());
        Story story;
        if (!equalsNameStory.isEmpty())
            story = equalsNameStory.get(0);
        else {
            Story storyTmp = new Story();
            storyTmp.setName(nameStory);

            authorsStory.forEach(author -> {if(author.getId() == 0) authorService.saveEntity(author);
            storyTmp.addAuthor(author);});
            story = storyTmp;
        }
        lastName = null;
        firstName = null;
        middleName = null;
        nameStory = null;
        authorsStory.clear();

        return story;
    }
}
