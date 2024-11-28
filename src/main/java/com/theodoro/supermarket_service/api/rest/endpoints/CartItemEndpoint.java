package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.CartItemAssembler;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import com.theodoro.supermarket_service.models.requests.CartItemRequest;
import com.theodoro.supermarket_service.models.responses.CartItemResponse;
import com.theodoro.supermarket_service.models.services.CartItemService;
import com.theodoro.supermarket_service.models.services.CartService;
import com.theodoro.supermarket_service.models.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_ITEM_ID_NOT_FOUND;

@RestController
public class CartItemEndpoint {
    public static final String CART_ITEM_RESOURCE_PATH = "/cart-items";
    public static final String CART_ITEM_ADD_PATH = CART_ITEM_RESOURCE_PATH + "/add";

    private final CartItemService cartItemService;
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemAssembler cartItemAssembler;

    public CartItemEndpoint(CartItemService cartItemService, CartService cartService, ProductService productService, CartItemAssembler cartItemAssembler) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemAssembler = cartItemAssembler;
    }

    @PostMapping(CART_ITEM_ADD_PATH)
    public ResponseEntity<URI> addItemToCart(@RequestBody @Valid CartItemRequest cartItemRequest) {
        return null;
    }

    public ResponseEntity<CartItemResponse> findById(String id) {
        CartItem cartItem = cartItemService.findById(id).orElseThrow(() -> new NotFoundException(CART_ITEM_ID_NOT_FOUND));
        return ResponseEntity.ok(cartItemAssembler.toModel(cartItem));
    }
}
