package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.OrderAssembler;
import com.theodoro.supermarket_service.api.rest.assemblers.OrderItemAssembler;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderResponse;
import com.theodoro.supermarket_service.api.rest.models.services.CartService;
import com.theodoro.supermarket_service.api.rest.models.services.OrderService;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.Order;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_ID_NOT_FOUND;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.ORDER_ID_NOT_FOUND;

@RestController
public class OrderEndpoint {
    public static final String ORDER_RESOURCE_PATH = "/orders";
    public static final String ORDER_SELF_PATH = ORDER_RESOURCE_PATH + "/{id}";

    private final OrderService orderService;
    private final CartService cartService;
    private final OrderAssembler orderAssembler;
    private final OrderItemAssembler orderItemAssembler;

    @Autowired
    public OrderEndpoint(OrderService orderService, CartService cartService, OrderAssembler orderAssembler, OrderItemAssembler orderItemAssembler) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.orderAssembler = orderAssembler;
        this.orderItemAssembler = orderItemAssembler;
    }

    @PostMapping(ORDER_RESOURCE_PATH)
    public ResponseEntity<URI> buy(@RequestParam(name = "idCart") String idCart){
        Cart cart = this.cartService.findById(idCart).orElseThrow(() -> new NotFoundException(CART_ID_NOT_FOUND));
        List<OrderItem> orderItems = this.orderItemAssembler.toEntityList(cart.getItems());
        Order order = orderService.buy(cart, orderItems);

        return ResponseEntity.created(orderAssembler.buildSelfLink(order.getId()).toUri()).build();
    }

    @GetMapping(ORDER_SELF_PATH)
    public ResponseEntity<OrderResponse> findById(@PathVariable("id") final String id){
        Order order = orderService.findById(id).orElseThrow(() -> new NotFoundException(ORDER_ID_NOT_FOUND));
        return ResponseEntity.ok(orderAssembler.toModel(order));
    }
}
