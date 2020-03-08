package ru.smith.dao;

import ru.smith.models.Publisher;

public class PublisherDaoImplHibernate extends EntityDaoHibernate{
    public PublisherDaoImplHibernate() {
        classEntity = Publisher.class;
    }
}
