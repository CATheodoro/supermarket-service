package com.theodoro.supermarket_service.models.services;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.models.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public Page<Cart> findAll(Pageable page) {
        return this.cartRepository.findAll(page);
    }

    public Optional<Cart> findById(String id) {
        return this.cartRepository.findById(id);
    }

    public void clearCart(Cart cart) {
        cartItemRepository.deleteAllByIdCart(cart.getId());
        cartRepository.delete(cart);
    }

}
