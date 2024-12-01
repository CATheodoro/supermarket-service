package com.theodoro.supermarket_service.api.rest.models.repositories;

import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    Optional<Promotion> findFirstByIdProduct(String idProduct);

    Optional<Promotion> findFirstByIdProductAndActive(String idProduct, boolean b);
}
