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

public class Main {

    public static void main(String[] args) {

        var sessionFactory = HibernateUtil.getSessionFactory();

        // repositories
        var containerRepo = new ContainerRepository(sessionFactory);
        var productRepo = new ProductRepository(sessionFactory);
        var courierRepo = new CourierRepository(sessionFactory);
        var deliveryRepo = new DeliveryRepository(sessionFactory);

        // services
        var containerService = new ContainerService(containerRepo);
        var productService = new ProductService(productRepo);
        var courierService = new CourierService(courierRepo);
        var deliveryService = new DeliveryService(deliveryRepo);

        // controllers
        var containerController = new ContainerController(containerService);
        var productController = new ProductController(productService, containerService);
        var courierController = new CourierController(courierService);
        var deliveryController = new DeliveryController(deliveryService, productService, courierService
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


