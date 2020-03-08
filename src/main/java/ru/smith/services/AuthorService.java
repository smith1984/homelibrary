package ru.smith.services;

import ru.smith.annotations.Entities;
import ru.smith.dao.AuthorDaoImplHibernate;

@ru.smith.annotations.EntityService(Entities.AUTHOR)
public class AuthorService extends EntityService {
    public AuthorService() {
        nameEntity = "Author";
        entityDaoHibernate = new AuthorDaoImplHibernate();
    }
}
