package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.CartItemEndpoint;
import com.theodoro.supermarket_service.api.rest.models.responses.CartItemResponse;
import com.theodoro.supermarket_service.api.rest.models.responses.ProductResponse;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartItemAssembler extends RepresentationModelAssemblerSupport<CartItem, CartItemResponse> {

    private final ProductAssembler productAssembler;

    public CartItemAssembler(ProductAssembler productAssembler){
        super(CartItemEndpoint.class, CartItemResponse.class);
        this.productAssembler = productAssembler;
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(CartItemEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public CartItemResponse toModel(@NonNull CartItem entity) {
        final CartItemResponse cartItemResponse = new CartItemResponse(entity);
        cartItemResponse.add(this.buildSelfLink(entity.getId()));
        return cartItemResponse;
    }

    @NonNull
    public CartItemResponse toModel(@NonNull CartItem entity, @NonNull Product product) {
        final ProductResponse productResponse = productAssembler.toModel(product);
        final CartItemResponse cartItemResponse = new CartItemResponse(entity, productResponse);
        cartItemResponse.add(this.buildSelfLink(entity.getId()));

        return cartItemResponse;
    }
}