package ru.smith.utils;

import ru.smith.models.Author;
import ru.smith.models.Story;

import java.util.List;


public class StringCreate {
    public static String createStrAuthors(List<Author> authors, String sep) {
        StringBuilder allAuthors = new StringBuilder();
        for (Author author : authors) {
            String fio = author.getLastName() + " " +
                    (author.getFirstName() != null ?
                            (author.getFirstName().substring(0, 1).toUpperCase() + ". ") : "") +
                    (author.getMiddleName() != null ? (
                            author.getMiddleName().substring(0, 1).toUpperCase() + ". ") : "") + sep;
            allAuthors.append(fio);
        }
        return allAuthors.substring(0, allAuthors.length() - sep.length());
    }

    public static String createStrStories(List<Story> stories, String sep){
        StringBuilder allStories = new StringBuilder();
        stories.forEach(story -> allStories.append(story.getName() + sep));
        return allStories.substring(0, allStories.length() - sep.length());
    }
}
