package ru.smith.services;

import ru.smith.annotations.Entities;
import ru.smith.dao.PublisherDaoImplHibernate;

@ru.smith.annotations.EntityService(Entities.PUBLISHER)
public class PublisherService extends EntityService{
    public PublisherService() {
        nameEntity = "Publisher";
        entityDaoHibernate = new PublisherDaoImplHibernate();
    }
}
