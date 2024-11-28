package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.CartEndpoint;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.models.responses.CartResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartAssembler  extends RepresentationModelAssemblerSupport<Cart, CartResponse> {

    public CartAssembler(){
        super(CartEndpoint.class, CartResponse.class);
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(CartEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public CartResponse toModel(@NonNull Cart entity) {
        final CartResponse cartResponse = new CartResponse(entity);
        cartResponse.add(this.buildSelfLink(entity.getId()));
        return cartResponse;
    }
}
