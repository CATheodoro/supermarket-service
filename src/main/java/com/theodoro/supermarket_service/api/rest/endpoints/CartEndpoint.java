package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.CartAssembler;
import com.theodoro.supermarket_service.api.rest.assemblers.CartItemAssembler;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import com.theodoro.supermarket_service.models.requests.CartRequest;
import com.theodoro.supermarket_service.models.responses.CartItemResponse;
import com.theodoro.supermarket_service.models.responses.CartResponse;
import com.theodoro.supermarket_service.models.services.CartItemService;
import com.theodoro.supermarket_service.models.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_CLEAR_SUCCESSFUL;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_ID_NOT_FOUND;

@RestController
public class CartEndpoint {
    public static final String CART_RESOURCE_PATH = "/carts";
    public static final String CART_SELF_PATH = CART_RESOURCE_PATH + "/{id}";
    public static final String CART_CLEAR_PATH = CART_SELF_PATH + "/clear";

    private final CartService cartService;
    private final CartAssembler cartAssembler;
    private final CartItemService cartItemService;

    @Autowired
    public CartEndpoint(CartService cartService, CartAssembler cartAssembler, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartAssembler = cartAssembler;
        this.cartItemService = cartItemService;
    }

    @PostMapping(CART_RESOURCE_PATH)
    public ResponseEntity<URI> create(@RequestBody @Valid CartRequest cartRequest){
        Cart product = this.cartService.create(cartAssembler.toEntity(cartRequest));
        return ResponseEntity.created(cartAssembler.buildSelfLink(product.getId()).toUri()).build();
    }

    @GetMapping(CART_RESOURCE_PATH)
    public ResponseEntity<Page<CartResponse>> findAll(@PageableDefault(size = 20, page = 0) Pageable page){
        Page<Cart> carts = this.cartService.findAll(page);
        return ResponseEntity.ok(cartAssembler.toPageModel(carts));
    }

    @GetMapping(CART_SELF_PATH)
    public ResponseEntity<CartResponse> findById(@PathVariable("id") final String id) {
        Cart cart = this.cartService.findById(id).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));

        return ResponseEntity.ok(cartAssembler.toModel(cart));
    }

    @DeleteMapping(CART_CLEAR_PATH)
    public ResponseEntity<?> clearCart(@PathVariable("id") final String id) {
        Cart cart = this.cartService.findById(id).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));
        cartService.clearCart(cart);

        return ResponseEntity.ok(CART_CLEAR_SUCCESSFUL.getMessage());
    }

}
