package com.theodoro.supermarket_service.models.services;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.models.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart create() {
        return cartRepository.save(new Cart());
    }

    public Optional<Cart> findById(String id) {
        return this.cartRepository.findById(id);
    }

    public void clearCart(Cart cart) {
        cartItemRepository.deleteAllByIdCart(cart.getId());
        cartRepository.delete(cart);
    }
}
