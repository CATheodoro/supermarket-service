package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.OrderItemEndpoint;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderItemResponse;
import com.theodoro.supermarket_service.api.rest.models.responses.ProductResponse;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemAssembler extends RepresentationModelAssemblerSupport<OrderItem, OrderItemResponse> {

    private final ProductAssembler productAssembler;

    public OrderItemAssembler(ProductAssembler productAssembler){
        super(OrderItemEndpoint.class, OrderItemResponse.class);
        this.productAssembler = productAssembler;
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(OrderItemEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public OrderItemResponse toModel(@NonNull OrderItem entity) {
        final OrderItemResponse orderItemResponse = new OrderItemResponse(entity);
        orderItemResponse.add(this.buildSelfLink(entity.getId()));
        return orderItemResponse;
    }

    @NonNull
    public OrderItemResponse toModel(@NonNull OrderItem entity, @NonNull Product product) {
        final ProductResponse productResponse = productAssembler.toModel(product);
        final OrderItemResponse orderItemResponse = new OrderItemResponse(entity, productResponse);
        orderItemResponse.add(this.buildSelfLink(entity.getId()));

        return orderItemResponse;
    }

    public OrderItem toEntity(CartItem cartItem) {
        return new OrderItem(cartItem);
    }

    public List<OrderItem> toEntityList(@NonNull List<CartItem> cartItems) {
        return cartItems.stream().map(this::toEntity).toList();
    }
}
