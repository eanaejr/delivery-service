package com.example;

import com.example.controller.*;
import com.example.repository.ContainerRepository;
import com.example.repository.CourierRepository;
import com.example.repository.DeliveryRepository;
import com.example.repository.ProductRepository;
import com.example.service.ContainerService;
import com.example.service.CourierService;
import com.example.service.DeliveryService;
import com.example.service.ProductService;
import com.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // repositories
        ContainerRepository containerRepo = new ContainerRepository(sessionFactory);
        ProductRepository productRepo = new ProductRepository(sessionFactory);
        CourierRepository courierRepo = new CourierRepository(sessionFactory);
        DeliveryRepository deliveryRepo = new DeliveryRepository(sessionFactory);

        // services
        ContainerService containerService = new ContainerService(containerRepo);
        ProductService productService = new ProductService(productRepo);
        CourierService courierService = new CourierService(courierRepo);
        DeliveryService deliveryService = new DeliveryService(deliveryRepo);

        // controllers
        ContainerController containerController = new ContainerController(containerService);
        ProductController productController = new ProductController(productService, containerService);
        CourierController courierController = new CourierController(courierService);
        DeliveryController deliveryController = new DeliveryController(deliveryService, productService, courierService
        );

        new MainController(
                containerController,
                courierController,
                productController,
                deliveryController
        ).run();

        HibernateUtil.shutdown();
    }
}


