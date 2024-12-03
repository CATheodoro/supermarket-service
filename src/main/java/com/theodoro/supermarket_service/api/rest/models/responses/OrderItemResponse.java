package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;

@JsonPropertyOrder({
        "id",
        "quantity",
        "unitPrice",
        "idOrder",
        "product",
        "creationDate"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "OrderItem", collectionRelation = "OrderItems")
public class OrderItemResponse extends RepresentationModel<OrderItemResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("unitPrice")
    private Integer unitPrice;
    @JsonProperty("idCart")
    private String idOrder;

    @JsonProperty("product")
    private ProductResponse productResponse;

    @JsonProperty("creationDate")
    private ZonedDateTime creationDate;

    public OrderItemResponse(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.idOrder = orderItem.getOrder().getId();
        this.creationDate = orderItem.getCreationDate();
    }

    public OrderItemResponse(OrderItem orderItem, ProductResponse productResponse) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.idOrder = orderItem.getOrder().getId();
        this.productResponse = productResponse;
        this.creationDate = orderItem.getCreationDate();
    }
}
