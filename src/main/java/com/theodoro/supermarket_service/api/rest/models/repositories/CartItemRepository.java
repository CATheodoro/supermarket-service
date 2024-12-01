package com.theodoro.supermarket_service.api.rest.models.repositories;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    void deleteAllByCart(Cart cart);

    List<CartItem> findByCart(Cart cart);
}
