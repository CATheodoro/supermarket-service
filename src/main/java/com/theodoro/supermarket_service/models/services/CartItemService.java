package com.theodoro.supermarket_service.models.services;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.models.repositories.CartRepository;
import com.theodoro.supermarket_service.models.requests.CartItemRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CartItem addItemToCart(Cart cart, Product product, @Valid CartItemRequest cartItemRequest) {
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(id -> id.equals(product.getId()))
                .findFirst()
                .map(id -> new CartItem())
                .orElse(new CartItem(
                        cartItemRequest,
                        cart,
                        product
                ));

        if (cartItem.getId() != null){
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
        }
        cart.getItems().add(cartItem.getId());
        cartRepository.save(cart);
        return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> findById(String id) {
        return cartItemRepository.findById(id);
    }
}
