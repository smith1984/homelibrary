package ru.smith.services;


import ru.smith.annotations.Entities;
import ru.smith.dao.EntityDaoHibernate;
import ru.smith.models.Entity;

import java.io.Serializable;
import java.util.List;
@ru.smith.annotations.EntityService(Entities.ENTITY)
public class EntityService implements Serializable {

    protected EntityDaoHibernate entityDaoHibernate;
    protected String nameEntity;

    public void saveEntity(Entity entity) {
        entityDaoHibernate.save(entity);
    }

    public void updateEntity(Entity entity) {
        entityDaoHibernate.update(entity);
    }

    public void deleteEntity(Entity entity) {
        entityDaoHibernate.delete(entity);
    }

    public Entity findEntity(int id) {
        return entityDaoHibernate.findById(id);
    }

    public List<? extends Entity> findAll() {
        return entityDaoHibernate.findAll(nameEntity);
    }

    public List<? extends Entity> findWithCondition(String condition) {
        return entityDaoHibernate.findAllWithDetail(nameEntity, condition);
    }
}
