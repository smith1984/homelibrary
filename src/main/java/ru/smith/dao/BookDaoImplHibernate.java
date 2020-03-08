package ru.smith.dao;

import ru.smith.models.Book;

public class BookDaoImplHibernate extends EntityDaoHibernate {
    public BookDaoImplHibernate() {
        classEntity = Book.class;
    }
}
