package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.PromotionEndpoint;
import com.theodoro.supermarket_service.api.rest.models.requests.PromotionRequest;
import com.theodoro.supermarket_service.api.rest.models.responses.PromotionResponse;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PromotionAssembler extends RepresentationModelAssemblerSupport<Promotion, PromotionResponse> {

    public PromotionAssembler(){
        super(PromotionEndpoint.class, PromotionResponse.class);
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(PromotionEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public PromotionResponse toModel(@NonNull Promotion entity) {
        final PromotionResponse promotionResponse = new PromotionResponse(entity);
        promotionResponse.add(this.buildSelfLink(entity.getId()));
        return promotionResponse;
    }

    public Promotion toEntity(PromotionRequest request) {
        return new Promotion(request);
    }

    public List<PromotionResponse> toList(List<Promotion> promotions) {
        return promotions.stream().map(this::toModel).toList();
    }
}
