package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.OrderItemRepository;
import com.theodoro.supermarket_service.api.rest.models.repositories.OrderRepository;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.Order;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public Order buy(Cart cart, List<OrderItem> orderItems) {
        Order order = new Order(cart);
        orderRepository.save(order);

        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        orderItems = orderItemRepository.saveAll(orderItems);
        order.getItems().addAll(orderItems);

        return orderRepository.save(order);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }
}
