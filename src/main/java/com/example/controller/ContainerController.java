package com.example.controller;

import com.example.service.ContainerService;

import java.util.Scanner;

public class ContainerController {

    private final ContainerService service;
    private final Scanner scanner = new Scanner(System.in);

    public ContainerController(ContainerService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Container Manager ---");
            System.out.println("1. Add container");
            System.out.println("2. List containers");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1" -> add();
                case "2" -> list();
                case "3" -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void add() {
        System.out.print("Latitude: ");
        double lat = Double.parseDouble(scanner.nextLine());
        System.out.print("Longitude: ");
        double lng = Double.parseDouble(scanner.nextLine());

        service.addContainer(lat, lng);
        System.out.println("Container added.");
    }

    private void list() {
        service.getAllContainers()
                .forEach(c -> System.out.println(
                        c.getId() + " | lat=" + c.getLat() + " | lng=" + c.getLng()
                ));
    }
}
