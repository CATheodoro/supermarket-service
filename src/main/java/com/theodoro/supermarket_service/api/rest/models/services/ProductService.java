package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.api.rest.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Page<Product> findAll(Pageable page) {
     return this.productRepository.findAll(page);
    }

    public Optional<Product> findById(String id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findAllById(List<String> id) {
        return this.productRepository.findAllById(id);
    }
}
