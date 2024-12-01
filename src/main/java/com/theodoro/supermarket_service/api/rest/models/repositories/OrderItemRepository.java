package com.theodoro.supermarket_service.api.rest.models.repositories;

import com.theodoro.supermarket_service.domains.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
