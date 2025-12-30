package com.example.repository;

import com.example.model.Courier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CourierRepository {

    private final SessionFactory sessionFactory;

    public CourierRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Courier courier) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(courier);
            tx.commit();
        }
    }

    public void update(Courier courier) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(courier);
            tx.commit();
        }
    }

    public Courier findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Courier.class, id);
        }
    }

    public List<Courier> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Courier", Courier.class).list();
        }
    }
}
