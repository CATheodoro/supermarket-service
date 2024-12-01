package com.theodoro.supermarket_service.api.rest.models.repositories;

import com.theodoro.supermarket_service.domains.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
