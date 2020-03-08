package ru.smith.services;

import ru.smith.annotations.Entities;
import ru.smith.dao.BookDaoImplHibernate;

@ru.smith.annotations.EntityService(Entities.BOOK)
public class BookService extends EntityService{
    public BookService() {
        nameEntity = "Book";
        entityDaoHibernate = new BookDaoImplHibernate();
    }
}
