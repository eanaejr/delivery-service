package com.example.controller;

import com.example.model.Courier;
import com.example.model.Delivery;
import com.example.model.Product;
import com.example.service.CourierService;
import com.example.service.DeliveryService;
import com.example.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class DeliveryController {

    private final DeliveryService deliveryService;
    private final ProductService productService;
    private final CourierService courierService;
    private final Scanner scanner = new Scanner(System.in);

    public DeliveryController(
            DeliveryService deliveryService,
            ProductService productService,
            CourierService courierService
    ) {
        this.deliveryService = deliveryService;
        this.productService = productService;
        this.courierService = courierService;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Delivery Manager ---");
            System.out.println("1. Create delivery");
            System.out.println("2. List all deliveries");
            System.out.println("3. List pending deliveries");
            System.out.println("4. Mark delivery as delivered");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1" -> createDelivery();
                case "2" -> listAll();
                case "3" -> listPending();
                case "4" -> markDelivered();
                case "5" -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void createDelivery() {
        System.out.println("\nAvailable products:");
        productService.getAllProducts()
                .forEach(p -> System.out.println(
                        p.getId() + " | code=" + p.getCode() + " | delivered=" + p.getDelivered()
                ));

        System.out.print("Product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.getProductById(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("\nAvailable couriers:");
        courierService.getAllCouriers()
                .forEach(c -> System.out.println(
                        c.getId() + " | name=" + c.getName()
                ));

        System.out.print("Courier ID: ");
        int courierId = Integer.parseInt(scanner.nextLine());
        Courier courier = courierService.getCourierById(courierId);

        if (courier == null) {
            System.out.println("Courier not found.");
            return;
        }

        deliveryService.createDelivery(product, courier);
        System.out.println("Delivery created.");
    }

    private void listAll() {
        List<Delivery> deliveries = deliveryService.getAll();

        if (deliveries.isEmpty()) {
            System.out.println("No deliveries.");
            return;
        }

        deliveries.forEach(d -> System.out.println(format(d)));
    }

    private void listPending() {
        List<Delivery> deliveries = deliveryService.getPending();

        if (deliveries.isEmpty()) {
            System.out.println("No pending deliveries.");
            return;
        }

        deliveries.forEach(d -> System.out.println(format(d)));
    }

    private void markDelivered() {
        List<Delivery> pending = deliveryService.getPending();

        if (pending.isEmpty()) {
            System.out.println("No pending deliveries.");
            return;
        }

        pending.forEach(d -> System.out.println(format(d)));

        System.out.print("Delivery ID to mark as delivered: ");
        int id = Integer.parseInt(scanner.nextLine());

        Delivery delivery = pending.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);

        if (delivery == null) {
            System.out.println("Invalid delivery ID.");
            return;
        }

        deliveryService.markDelivered(delivery);
        System.out.println("Delivery marked as delivered.");
    }

    private String format(Delivery d) {
        return d.getId()
                + " | product=" + d.getProduct().getCode()
                + " | courier=" + d.getCourier().getName()
                + " | deliveredAt=" + d.getDeliveredTime();
    }
}
