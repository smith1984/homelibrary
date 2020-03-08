package ru.smith;

import ru.smith.models.*;
import ru.smith.services.*;

public class Main {
    public static void main(final String[] args) {
        EntityService bookService = new BookService();
        EntityService publisherService = new PublisherService();
        EntityService authorService = new AuthorService();
        EntityService storyService = new StoryService();
        EntityService seriesService = new SeriesService();

        Book book = new Book();
        book.setName("Моя первая книга");
        book.setPages(125);
        book.setYearPublishing(2019);

        Book book2 = new Book();
        book2.setName("Моя вторая книга");
        book2.setPages(75);
        book2.setYearPublishing(2019);

        Publisher publisher = new Publisher();
        publisher.setName("Росмэн");
        publisher.setCity("Москва");
        publisherService.saveEntity(publisher);
        publisher.addBook(book);
        publisher.addBook(book2);

        Author author = new Author();
        author.setFirstName("Максим");
        author.setLastName("Петров");
        author.setMiddleName("Петрович");

        Author author2 = new Author();
        author2.setFirstName("Максим2");
        author2.setLastName("Петров2");
        author2.setMiddleName("Петрович2");

        Author author3 = new Author();
        author3.setFirstName("Максим3");
        author3.setLastName("Петров3");
        author3.setMiddleName("Петрович3");


        Story story = new Story();
        story.setName("Мой первый рассказ");
        story.addAuthor(author);
        story.addAuthor(author2);

        Story story2 = new Story();
        story2.setName("Мой второй рассказ");
        story2.addAuthor(author);
        story2.addAuthor(author2);
        story2.addAuthor(author3);

        Story story3 = new Story();
        story3.setName("Мой третий рассказ");
        story3.addAuthor(author);

        Series series = new Series();
        series.setName("Моя серия книг");
        series.setPublisher(publisher);
        seriesService.saveEntity(series);
        series.addBook(book2);

        book.addStory(story);
        book.addStory(story2);
        book.addStory(story3);

        book2.addStory(story2);
        book2.setSeries(series);

        bookService.saveEntity(book);
        bookService.saveEntity(book2);
    }
}