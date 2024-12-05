package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.CartAssembler;
import com.theodoro.supermarket_service.api.rest.models.responses.CartResponse;
import com.theodoro.supermarket_service.api.rest.models.services.CartService;
import com.theodoro.supermarket_service.api.rest.models.services.ProductService;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_CLEAR_SUCCESSFUL;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_ID_NOT_FOUND;

@RestController
public class CartEndpoint {
    public static final String CART_RESOURCE_PATH = "/api/carts";
    public static final String CART_SELF_PATH = CART_RESOURCE_PATH + "/{id}";
    public static final String CART_CLEAR_PATH = CART_SELF_PATH + "/clear";

    private final CartService cartService;
    private final CartAssembler cartAssembler;
    private final ProductService productService;

    @Autowired
    public CartEndpoint(CartService cartService, CartAssembler cartAssembler, ProductService productService) {
        this.cartService = cartService;
        this.cartAssembler = cartAssembler;
        this.productService = productService;
    }

    @PostMapping(CART_RESOURCE_PATH)
    public ResponseEntity<URI> create(){
        Cart cart = this.cartService.create(new Cart(0, 0, 0));
        return ResponseEntity.created(cartAssembler.buildSelfLink(cart.getId()).toUri()).build();
    }

    @GetMapping(CART_RESOURCE_PATH)
    public ResponseEntity<Page<CartResponse>> findAll(@PageableDefault(size = 20, page = 0) Pageable page){
        Page<Cart> carts = this.cartService.findAll(page);
        List<String> productIds = carts.stream()
                .flatMap(cart -> cart.getItems().stream())
                .map(CartItem::getIdProduct).toList();
        List<Product> products = this.productService.findAllById(productIds);

        return ResponseEntity.ok(cartAssembler.toPageModel(carts, products));
    }

    @GetMapping(CART_SELF_PATH)
    public ResponseEntity<CartResponse> findById(@PathVariable("id") final String id) {
        Cart cart = this.cartService.findById(id).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));
        List<Product> product = this.productService.findAllById(cart.getItems().stream()
                .map(CartItem::getIdProduct).toList());
        return ResponseEntity.ok(cartAssembler.toModel(cart, product));
    }

    @DeleteMapping(CART_CLEAR_PATH)
    public ResponseEntity<?> clearCart(@PathVariable("id") final String id) {
        Cart cart = this.cartService.findById(id).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));
        cartService.clearCart(cart);

        return ResponseEntity.ok(CART_CLEAR_SUCCESSFUL.getMessage());
    }

}
