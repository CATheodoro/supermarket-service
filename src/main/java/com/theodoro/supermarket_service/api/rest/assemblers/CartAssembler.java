package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.CartEndpoint;
import com.theodoro.supermarket_service.api.rest.models.requests.CartRequest;
import com.theodoro.supermarket_service.api.rest.models.responses.CartItemResponse;
import com.theodoro.supermarket_service.api.rest.models.responses.CartResponse;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartAssembler extends RepresentationModelAssemblerSupport<Cart, CartResponse> {

    private final CartItemAssembler cartItemAssembler;

    public CartAssembler(CartItemAssembler cartItemAssembler){
        super(CartEndpoint.class, CartResponse.class);
        this.cartItemAssembler = cartItemAssembler;
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(CartEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public CartResponse toModel(@NonNull Cart entity) {
        List<CartItemResponse> cartItemResponses = entity.getItems().stream()
                .map(cartItemAssembler::toModel).toList();

        final CartResponse cartResponse = new CartResponse(entity, cartItemResponses);
        cartResponse.add(this.buildSelfLink(entity.getId()));
        return cartResponse;
    }

    @NonNull
    public CartResponse toModel(@NonNull Cart entity, @NonNull List<Product> products) {

        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        List<CartItemResponse> cartItemResponses = entity.getItems().stream()
                .map(cartItem -> {
                    Product product = productMap.get(cartItem.getIdProduct());
                    cartItemAssembler.toModel(cartItem, product);
                    return cartItemAssembler.toModel(cartItem, product);
                }).toList();

        final CartResponse cartResponse = new CartResponse(entity, cartItemResponses);
        cartResponse.add(this.buildSelfLink(entity.getId()));
        return cartResponse;
    }

    public Cart toEntity(CartRequest request) {
        Cart cart = new Cart();
        cart.setTotalPrice(request.getTotalPrice());
        cart.setItems(request.getItems());
        return cart;
    }

    public Page<CartResponse> toPageModel(Page<Cart> carts, List<Product> products) {
        return carts.map(cart -> this.toModel(cart, products));
    }
}
