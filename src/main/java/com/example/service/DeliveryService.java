package com.example.service;

import com.example.model.Courier;
import com.example.model.Delivery;
import com.example.model.Product;
import com.example.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(Product product, Courier courier) {
        Delivery delivery = new Delivery();
        delivery.setProduct(product);
        delivery.setCourier(courier);
        repository.save(delivery);
    }

    public void markDelivered(Delivery delivery) {
        delivery.setDeliveredTime(LocalDateTime.now());
        repository.save(delivery);
    }

    public List<Delivery> getAll() {
        return repository.findAll();
    }

    public List<Delivery> getPending() {
        return repository.findPending();
    }

    public List<Delivery> getDeliveredByCourier(Courier courier) {
        return repository.findDeliveredByCourier(courier);
    }
}
