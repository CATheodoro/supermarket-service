package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.CartItemEndpoint;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.models.responses.CartItemResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartItemAssembler extends RepresentationModelAssemblerSupport<CartItem, CartItemResponse> {

    public CartItemAssembler(){
        super(CartItemEndpoint.class, CartItemResponse.class);
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
}