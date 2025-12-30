package com.example.utils;

import com.example.model.Container;
import com.example.model.Courier;
import com.example.model.Delivery;
import com.example.model.Product;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Container.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Courier.class)
                    .addAnnotatedClass(Delivery.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
