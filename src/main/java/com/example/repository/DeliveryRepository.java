package com.example.repository;

import com.example.model.Courier;
import com.example.model.Delivery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DeliveryRepository {

    private final SessionFactory sessionFactory;

    public DeliveryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Delivery delivery) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            if (delivery.getId() == null) {
                session.persist(delivery); // novi zapis
            } else {
                session.merge(delivery);   // update postojeći
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }


    public List<Delivery> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Delivery", Delivery.class).list();
        }
    }

    public List<Delivery> findPending() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Delivery d WHERE d.deliveredTime IS NULL",
                    Delivery.class
            ).list();
        }
    }

    public List<Delivery> findDeliveredByCourier(Courier courier) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Delivery d WHERE d.courier = :c AND d.deliveredTime IS NOT NULL",
                            Delivery.class
                    ).setParameter("c", courier)
                    .list();
        }
    }
}
