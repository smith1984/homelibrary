package ru.smith.dao;

import ru.smith.models.Series;

public class SeriesDaoImplHibernate extends EntityDaoHibernate {
    public SeriesDaoImplHibernate() {
        classEntity = Series.class;
    }
}
