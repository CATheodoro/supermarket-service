package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import com.theodoro.supermarket_service.domains.enumerations.PromotionEnum;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;

@JsonPropertyOrder({
        "id",
        "code",
        "description",
        "idProduct",
        "active",
        "requiredQuantity",
        "price",
        "amount",
        "freeQuantity",
        "creationDate"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "Promotion", collectionRelation = "Promotions")
public class PromotionResponse extends RepresentationModel<PromotionResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("code")
    private PromotionEnum code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("idProduct")
    private String idProduct;
    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("requiredQuantity")
    private Integer requiredQuantity;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("amount")
    private Float amount;
    @JsonProperty("freeQuantity")
    private Integer freeQuantity;

    @JsonProperty("creationDate")
    private ZonedDateTime creationDate;

    public PromotionResponse(Promotion entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.description = entity.getDescription();
        this.idProduct = entity.getIdProduct();
        this.active = entity.getActive();
        this.requiredQuantity = entity.getRequiredQuantity();
        this.price = entity.getPrice();
        this.amount = entity.getAmount();
        this.freeQuantity = entity.getFreeQuantity();
        this.creationDate = entity.getCreationDate();
    }

}
