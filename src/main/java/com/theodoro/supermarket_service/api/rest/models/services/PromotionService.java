package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.PromotionRepository;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final WebClient webClient;

    public PromotionService(PromotionRepository promotionRepository, WebClient.Builder webClientBuilder) {
        this.promotionRepository = promotionRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
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


    public Optional<Promotion> findFindByIdWithWiremock(String id) {
        return Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/promotions-wiremock/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(Promotion.class)
                .block());
    }

    public List<Promotion> findAllWithWiremock() {
        Flux<Promotion> promotionsFlux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/promotions-wiremock")
                        .build())
                .retrieve()
                .bodyToFlux(Promotion.class);
        return promotionsFlux.collectList().block();
    }
}
