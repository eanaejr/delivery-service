package com.example.service;

import com.example.model.Container;
import com.example.repository.ContainerRepository;

import java.util.List;

public class ContainerService {

    private final ContainerRepository repository;

    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }

    public void addContainer(double lat, double lng) {
        Container container = new Container();
        container.setLat(lat);
        container.setLng(lng);
        repository.save(container);
    }

    public List<Container> getAllContainers() {
        return repository.findAll();
    }

    public Container getContainerById(int id) {
        return repository.findById(id);
    }
}
