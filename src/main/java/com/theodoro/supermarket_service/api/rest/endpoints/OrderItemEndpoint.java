package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.OrderItemAssembler;
import com.theodoro.supermarket_service.api.rest.models.responses.OrderItemResponse;
import com.theodoro.supermarket_service.api.rest.models.services.OrderItemService;
import com.theodoro.supermarket_service.api.rest.models.services.ProductService;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.ORDER_ITEM_ID_NOT_FOUND;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;

@RestController
public class OrderItemEndpoint {
    public static final String ORDER_ITEM_RESOURCE_PATH = "/api/order-items";
    public static final String ORDER_ITEM_SELF_PATH = ORDER_ITEM_RESOURCE_PATH + "/{id}";

    private final OrderItemService orderItemService;
    private final OrderItemAssembler orderItemAssembler;
    private final ProductService productService;

    @Autowired
    public OrderItemEndpoint(OrderItemService orderItemService, OrderItemAssembler orderItemAssembler, ProductService productService) {
        this.orderItemService = orderItemService;
        this.orderItemAssembler = orderItemAssembler;
        this.productService = productService;
    }

    @GetMapping(ORDER_ITEM_SELF_PATH)
    public ResponseEntity<OrderItemResponse> findById(@PathVariable("id") final String id){
        OrderItem orderItem = orderItemService.findById(id).orElseThrow(() -> new NotFoundException(ORDER_ITEM_ID_NOT_FOUND));
        Product product = productService.findById(orderItem.getIdProduct()).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));
        return ResponseEntity.ok(orderItemAssembler.toModel(orderItem, product));
    }

}
