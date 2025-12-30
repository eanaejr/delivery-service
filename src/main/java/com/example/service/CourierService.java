package com.example.service;

import com.example.model.Courier;
import com.example.repository.CourierRepository;

import java.util.List;

public class CourierService {

    private final CourierRepository repository;

    public CourierService(CourierRepository repository) {
        this.repository = repository;
    }

    public void addCourier(String name) {
        Courier courier = new Courier();
        courier.setName(name);
        courier.setTotalDistanceToday(0.0);
        repository.save(courier);
    }

    public void addDistance(int courierId, double distance) {
        Courier courier = repository.findById(courierId);
        if (courier == null) {
            throw new IllegalArgumentException("Courier not found");
        }

        courier.setTotalDistanceToday(
                courier.getTotalDistanceToday() + distance
        );

        repository.update(courier);
    }

    public Courier getCourierById(int id) {
        return repository.findById(id);
    }

    public List<Courier> getAllCouriers() {
        return repository.findAll();
    }
}
