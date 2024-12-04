package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.api.rest.models.repositories.CartRepository;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CalculateDiscountService calculateDiscountService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, CalculateDiscountService calculateDiscountService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.calculateDiscountService = calculateDiscountService;
    }

    public CartItem addItemToCart(Cart cart, CartItem cartItem, Integer cartItemsQuantity) {

        if (cartItem.getId() != null) {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemsQuantity);
        }

        this.calculateDiscountService.calculatePromotionDiscount(cart, cartItem);

        cartItem = cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return cartItem;
    }

    public Optional<CartItem> findById(String id) {
        return cartItemRepository.findById(id);
    }
}
