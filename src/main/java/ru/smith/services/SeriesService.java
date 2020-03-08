package ru.smith.services;

import ru.smith.annotations.Entities;
import ru.smith.dao.SeriesDaoImplHibernate;

@ru.smith.annotations.EntityService(Entities.SERIES)
public class SeriesService extends EntityService {
    public SeriesService() {
        nameEntity = "Series";
        entityDaoHibernate = new SeriesDaoImplHibernate();
    }
}
