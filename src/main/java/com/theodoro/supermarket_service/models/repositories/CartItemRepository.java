package com.theodoro.supermarket_service.models.repositories;

import com.theodoro.supermarket_service.domains.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    void deleteAllByIdCart(String id);
}
