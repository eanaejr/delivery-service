package com.example.controller;

import com.example.model.Courier;
import com.example.service.CourierService;

import java.util.List;
import java.util.Scanner;

public class CourierController {

    private final CourierService service;
    private final Scanner scanner = new Scanner(System.in);

    public CourierController(CourierService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Courier Manager ---");
            System.out.println("1. Add courier");
            System.out.println("2. List couriers");
            System.out.println("3. Add distance to courier");
            System.out.println("4. Back");
            System.out.print("Choose option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> addCourier();
                case "2" -> listCouriers();
                case "3" -> addDistance();
                case "4" -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addCourier() {
        System.out.print("Courier name: ");
        String name = scanner.nextLine();
        service.addCourier(name);
        System.out.println("Courier added.");
    }

    private void listCouriers() {
        List<Courier> couriers = service.getAllCouriers();
        if (couriers.isEmpty()) {
            System.out.println("No couriers found.");
            return;
        }

        couriers.forEach(c ->
                System.out.println(
                        c.getId() +
                                " | " + c.getName() +
                                " | distance today: " + c.getTotalDistanceToday()
                )
        );
    }

    private void addDistance() {
        System.out.print("Courier ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Distance to add: ");
        double distance = Double.parseDouble(scanner.nextLine());

        try {
            service.addDistance(id, distance);
            System.out.println("Distance added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
