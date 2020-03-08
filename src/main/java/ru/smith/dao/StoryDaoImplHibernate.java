package ru.smith.dao;

import ru.smith.models.Story;

public class StoryDaoImplHibernate extends EntityDaoHibernate {
    public StoryDaoImplHibernate() {
        classEntity = Story.class;
    }
}
