package ru.smith.dao;

import ru.smith.models.Author;

public class AuthorDaoImplHibernate extends EntityDaoHibernate {
    public AuthorDaoImplHibernate() {
        classEntity = Author.class;
    }
}
