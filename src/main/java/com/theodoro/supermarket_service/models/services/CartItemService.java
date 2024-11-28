package com.theodoro.supermarket_service.models.services;

import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.models.repositories.CartItemRepository;
import com.theodoro.supermarket_service.models.repositories.CartRepository;
import com.theodoro.supermarket_service.models.requests.CartItemRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public CartItem addItemToCart(Cart cart, Product product, Integer cartItemsQuantity) {
//        CartItem cartItem = cart.getItems()
//                .stream()
//                .filter(item -> item.getProduct().getId().equals(product.getId()))
//                .findFirst().orElse(new CartItem());
//        if (cartItem.getId() == null) {
//            cartItem.setIdCart(cart.getId());
//            cartItem.setProduct(product);
//            cartItem.setQuantity(cartItemsQuantity);
//            cartItem.setUnitPrice(product.getPrice());
//        }
//        else {
//            cartItem.setQuantity(cartItem.getQuantity() + cartItemsQuantity);
//        }
//        cart.getItems().add(cartItem);
//        cartRepository.save(cart);
//        return cartItemRepository.save(cartItem);
        return null;
    }

    public Optional<CartItem> findById(String id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findByIdCart(String idItems) {
        return cartItemRepository.findByIdCart(idItems);
    }
}
