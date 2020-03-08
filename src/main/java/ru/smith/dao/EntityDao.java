package ru.smith.dao;

import ru.smith.models.Entity;

import java.util.List;

interface EntityDao {
    List<Entity> findAll(String nameEntity);
    List<Entity> findAllWithDetail(String nameEntity, String condition);
    Entity findById(int id);
    void save(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
