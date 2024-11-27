package com.theodoro.supermarket_service.models.services;

import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(String id) {
        return this.productRepository.findById(id);
    }
}
