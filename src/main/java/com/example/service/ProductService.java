package com.example.service;

import com.example.model.Container;
import com.example.model.Product;
import com.example.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String code, Container container) {
        Product product = new Product();
        product.setCode(code);
        product.setContainer(container);
        product.setDelivered(false);
        product.setArrivalTime(LocalDateTime.now());

        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id);
    }
}
