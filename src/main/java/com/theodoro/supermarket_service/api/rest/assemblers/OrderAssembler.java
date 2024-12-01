package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.OrderEndpoint;
import com.theodoro.supermarket_service.domains.entities.Order;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderItemResponse;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderResponse;
import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Order, OrderResponse> {

    private final OrderItemAssembler orderItemAssembler;

    public OrderAssembler(OrderItemAssembler orderItemAssembler){
        super(OrderEndpoint.class, OrderResponse.class);
        this.orderItemAssembler = orderItemAssembler;
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(OrderEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public OrderResponse toModel(@NonNull Order entity) {
        List<OrderItemResponse> orderItemResponses = entity.getItems().stream()
                .map(orderItemAssembler::toModel).toList();

        final OrderResponse orderResponse = new OrderResponse(entity, orderItemResponses);
        orderResponse.add(this.buildSelfLink(entity.getId()));
        return orderResponse;
    }
}
