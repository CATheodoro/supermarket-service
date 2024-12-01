package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.OrderItem;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@JsonPropertyOrder({
        "id",
        "quantity",
        "unitPrice",
        "idProduct"
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

    @JsonProperty("idProduct")
    private String idProduct;

    public OrderItemResponse(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.idProduct = orderItem.getIdProduct();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
