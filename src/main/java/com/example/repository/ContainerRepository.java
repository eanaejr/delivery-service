package com.example.repository;

import com.example.model.Container;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ContainerRepository {

    private final SessionFactory sessionFactory;

    public ContainerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Container container) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(container);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Container> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Container", Container.class).list();
        }
    }

    public Container findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Container.class, id);
        }
    }
}
