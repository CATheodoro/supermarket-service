package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.api.rest.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.api.rest.models.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public CartItem addItemToCart(Cart cart, Product product, CartItem cartItem, Integer cartItemsQuantity) {

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setIdProduct(product.getId());
            cartItem.setQuantity(cartItemsQuantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemsQuantity);
        }
        cart.setTotalPrice(product.getPrice() * cartItemsQuantity);
        cart.getItems().add(cartItem);
        cartItem = cartItemRepository.save(cartItem);
        cartRepository.save(cart);
        return cartItem;
    }

    public Optional<CartItem> findById(String id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }
}
