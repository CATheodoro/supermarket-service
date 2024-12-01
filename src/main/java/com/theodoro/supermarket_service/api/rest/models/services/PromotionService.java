package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.PromotionRepository;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion save(Promotion role) {
        return promotionRepository.save(role);
    }

    public Promotion create(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> findById(String id) {
        return promotionRepository.findById(id);
    }
}
