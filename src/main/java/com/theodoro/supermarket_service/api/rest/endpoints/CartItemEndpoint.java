package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.CartItemAssembler;
import com.theodoro.supermarket_service.api.rest.models.requests.CartItemRequest;
import com.theodoro.supermarket_service.api.rest.models.responses.CartItemResponse;
import com.theodoro.supermarket_service.api.rest.models.services.CartItemService;
import com.theodoro.supermarket_service.api.rest.models.services.CartService;
import com.theodoro.supermarket_service.api.rest.models.services.ProductService;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.*;

@RestController
public class CartItemEndpoint {
    public static final String CART_ITEM_RESOURCE_PATH = "/cart-items";
    public static final String CART_ITEM_ADD_PATH = CART_ITEM_RESOURCE_PATH + "/add";
    public static final String CART_ITEM_SELF_PATH = CART_ITEM_RESOURCE_PATH + "/{id}";

    private final CartItemService cartItemService;
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemAssembler cartItemAssembler;

    @Autowired
    public CartItemEndpoint(CartItemService cartItemService, CartService cartService, ProductService productService, CartItemAssembler cartItemAssembler) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemAssembler = cartItemAssembler;
    }

    @PostMapping(CART_ITEM_ADD_PATH)
    public ResponseEntity<URI> addItemToCart(@RequestBody @Valid CartItemRequest cartItemRequest) {
        Cart cart = this.cartService.findById(cartItemRequest.getIdCart()).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));
        Product product = this.productService.findById(cartItemRequest.getIdProduct()).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getIdProduct().equals(product.getId()))
                .findFirst()
                .orElse(new CartItem(
                        cart,
                        product,
                        cartItemRequest.getQuantity()));

        cartItem = cartItemService.addItemToCart(cart, cartItem, cartItemRequest.getQuantity());
        return ResponseEntity.created(cartItemAssembler.buildSelfLink(cartItem.getId()).toUri()).build();
    }

    @GetMapping(CART_ITEM_SELF_PATH)
    public ResponseEntity<CartItemResponse> findById(@PathVariable("id") final String id) {
        CartItem cartItem = cartItemService.findById(id).orElseThrow(() -> new NotFoundException(CART_ITEM_ID_NOT_FOUND));
        Product product = productService.findById(cartItem.getIdProduct()).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));

        return ResponseEntity.ok(cartItemAssembler.toModel(cartItem, product));
    }
}
