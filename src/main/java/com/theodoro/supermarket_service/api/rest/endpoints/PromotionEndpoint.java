package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.PromotionAssembler;
import com.theodoro.supermarket_service.api.rest.models.requests.PromotionRequest;
import com.theodoro.supermarket_service.api.rest.models.responses.PromotionResponse;
import com.theodoro.supermarket_service.api.rest.models.services.ProductService;
import com.theodoro.supermarket_service.api.rest.models.services.PromotionService;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PROMOTION_ID_NOT_FOUND;

@RestController
public class PromotionEndpoint {
    public static final String PROMOTION_RESOURCE_PATH = "/promotions";
    public static final String PROMOTION_SELF_PATH = PROMOTION_RESOURCE_PATH + "/{id}";

    private final PromotionService promotionService;
    private final PromotionAssembler promotionAssembler;
    private final ProductService productService;

    @Autowired
    public PromotionEndpoint(PromotionService promotionService, PromotionAssembler promotionAssembler, ProductService productService) {
        this.promotionService = promotionService;
        this.promotionAssembler = promotionAssembler;
        this.productService = productService;
    }

    @PostMapping(PROMOTION_RESOURCE_PATH)
    public ResponseEntity<URI> create(@RequestBody @Valid PromotionRequest promotionRequest){
        this.productService.findById(promotionRequest.getIdProduct()).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));
        Promotion promotion = this.promotionService.create(promotionAssembler.toEntity(promotionRequest));
        return ResponseEntity.created(promotionAssembler.buildSelfLink(promotion.getId()).toUri()).build();
    }

    @GetMapping(PROMOTION_RESOURCE_PATH)
    public ResponseEntity<List<PromotionResponse>> findAll(){
        List<Promotion> promotions = this.promotionService.findAll();
        return ResponseEntity.ok(promotionAssembler.toList(promotions));
    }

    @GetMapping(PROMOTION_SELF_PATH)
    public ResponseEntity<PromotionResponse> findById(@PathVariable("id") final String id) {
        Promotion promotion = this.promotionService.findById(id).orElseThrow(() -> new NotFoundException(PROMOTION_ID_NOT_FOUND));
        return ResponseEntity.ok(promotionAssembler.toModel(promotion));
    }
}
