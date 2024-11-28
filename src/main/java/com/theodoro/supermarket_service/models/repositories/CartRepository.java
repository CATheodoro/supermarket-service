package com.theodoro.supermarket_service.models.repositories;

import com.theodoro.supermarket_service.domains.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}
