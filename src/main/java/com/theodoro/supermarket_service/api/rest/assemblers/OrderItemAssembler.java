package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.OrderItemEndpoint;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderItemResponse;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemAssembler extends RepresentationModelAssemblerSupport<OrderItem, OrderItemResponse> {

    public OrderItemAssembler(){
        super(OrderItemEndpoint.class, OrderItemResponse.class);
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

    public OrderItemResponse toModel(@NonNull CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(cartItem.getId());
        orderItem.setIdProduct(cartItem.getIdProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setUnitPrice(cartItem.getUnitPrice());

        return this.toModel(orderItem);
    }

    public OrderItem toEntity(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setUnitPrice(cartItem.getUnitPrice());
        orderItem.setIdProduct(cartItem.getIdProduct());
        return orderItem;
    }

    public List<OrderItem> toEntityList(@NonNull List<CartItem> cartItems) {
        return cartItems.stream().map(this::toEntity).toList();
    }
}
