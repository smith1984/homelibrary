package ru.smith.services;

import ru.smith.annotations.Entities;
import ru.smith.dao.StoryDaoImplHibernate;

@ru.smith.annotations.EntityService(Entities.STORY)
public class StoryService extends EntityService {
    public StoryService() {
        nameEntity = "Story";
        entityDaoHibernate = new StoryDaoImplHibernate();
    }
}
