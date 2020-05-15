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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Entity> list = null;
        try{
        list = session.createQuery("From " + nameEntity).list();}
        catch(Exception e){e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    public List<Entity> findAllWithDetail(String nameEntity, String condition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Entity> list = null;
        try{
            list = session.createQuery("From " + nameEntity + " where " + condition).list();}
        catch(Exception e){e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    public Entity findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Entity entity = null;
        try{
            entity = session.get(classEntity, id);}
        catch(Exception e){e.printStackTrace();
        }
        finally {
            session.close();
        }
        return entity;
    }
}
