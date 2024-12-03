package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.OrderItemRepository;
import com.theodoro.supermarket_service.api.rest.models.repositories.OrderRepository;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.Order;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    @Transactional
    public Order buy(Cart cart, List<OrderItem> orderItems) {
        Order order = orderRepository.save(new Order(cart));

        orderItems.forEach(orderItem ->{
            orderItem.setOrder(order);
            productService.reduceStock(orderItem.getIdProduct(), orderItem.getQuantity());
        });

        orderItems = orderItemRepository.saveAll(orderItems);
        order.getItems().addAll(orderItems);

        return orderRepository.save(order);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }
}
