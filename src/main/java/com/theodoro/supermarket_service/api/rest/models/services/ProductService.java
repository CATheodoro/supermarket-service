package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.ProductRepository;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.BadRequestException;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_NOT_ENOUGH_STOCK_BAD_REQUEST;

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

    @Transactional
    public void reduceStock(String productId, Integer quantity) {
        Product product = this.findById(productId).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));

        if (product.getStockQuantity() < quantity) {
            throw new BadRequestException(PRODUCT_NOT_ENOUGH_STOCK_BAD_REQUEST);
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
    }

    @Transactional
    public void addStock(String productId, Integer quantity) {
        Product product = this.findById(productId).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));

        if (product.getStockQuantity() < quantity) {
            throw new BadRequestException(PRODUCT_NOT_ENOUGH_STOCK_BAD_REQUEST);
        }

        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);
    }
}
