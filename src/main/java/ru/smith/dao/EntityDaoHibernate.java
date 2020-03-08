package ru.smith.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.smith.models.Entity;
import ru.smith.utils.HibernateSessionFactoryUtil;
import java.util.List;

public class EntityDaoHibernate implements EntityDao {
    protected Class<? extends Entity> classEntity;

    public void save(Entity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    public void update(Entity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    public void delete(Entity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    public List<Entity> findAll(String nameEntity) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From " + nameEntity).list();
    }

    public List<Entity> findAllWithDetail(String nameEntity, String condition) {

        return HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From " + nameEntity + " where " + condition).list();
    }

    public Entity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(classEntity, id);
    }
}
