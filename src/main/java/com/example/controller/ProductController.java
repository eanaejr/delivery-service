package com.example.controller;

import com.example.model.Container;
import com.example.service.ContainerService;
import com.example.service.ProductService;

import java.util.Scanner;

public class ProductController {

    private final ProductService productService;
    private final ContainerService containerService;
    private final Scanner scanner = new Scanner(System.in);

    public ProductController(ProductService productService, ContainerService containerService) {
        this.productService = productService;
        this.containerService = containerService;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Product Manager ---");
            System.out.println("1. Add product");
            System.out.println("2. List products");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1" -> add();
                case "2" -> list();
                case "3" -> { return; }
            }
        }
    }

    private void add() {
        System.out.print("Product code: ");
        String code = scanner.nextLine();

        containerService.getAllContainers()
                .forEach(c -> System.out.println(c.getId() + ": " + c.getLat() + "," + c.getLng()));

        System.out.print("Container ID: ");
        int cid = Integer.parseInt(scanner.nextLine());
        Container container = containerService.getContainerById(cid);

        productService.addProduct(code, container);
        System.out.println("Product added.");
    }

    private void list() {
        productService.getAllProducts()
                .forEach(p -> System.out.println(
                        p.getId() + " | code=" + p.getCode() + " | delivered=" + p.getDelivered()
                ));
    }
}
