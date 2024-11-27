package com.theodoro.supermarket_service.models.repositories;

import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
