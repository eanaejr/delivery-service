package com.example.controller;

import java.util.Scanner;

public class MainController {

    private final ContainerController containerController;
    private final CourierController courierController;
    private final ProductController productController;
    private final DeliveryController deliveryController;

    private final Scanner scanner = new Scanner(System.in);

    public MainController(
            ContainerController containerController,
            CourierController courierController,
            ProductController productController,
            DeliveryController deliveryController
    ) {
        this.containerController = containerController;
        this.courierController = courierController;
        this.productController = productController;
        this.deliveryController = deliveryController;
    }

    public void run() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Containers");
            System.out.println("2. Couriers");
            System.out.println("3. Products");
            System.out.println("4. Deliveries");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> containerController.run();
                case "2" -> courierController.run();
                case "3" -> productController.run();
                case "4" -> deliveryController.run();
                case "5" -> {
                    System.out.println("Bye");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
