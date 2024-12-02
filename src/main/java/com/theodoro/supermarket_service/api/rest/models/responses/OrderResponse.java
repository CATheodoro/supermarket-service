package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Order;
import jakarta.persistence.Column;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "totalPrice",
        "discount",
        "finalPrice",
        "cartItems",
        "creationDate"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "Order", collectionRelation = "Orders")
public class OrderResponse extends RepresentationModel<OrderResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("totalPrice")
    private Integer totalPrice;
    @JsonProperty("discount")
    private Integer discount;
    @JsonProperty("finalPrice")
    private Integer finalPrice;

    @JsonProperty("orderItems")
    private List<OrderItemResponse> orderItems;

    @JsonProperty("creationDate")
    private ZonedDateTime creationDate;

    public OrderResponse(Order order, List<OrderItemResponse> orderItemResponses) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.discount = order.getDiscount();
        this.finalPrice = order.getFinalPrice();
        this.orderItems = orderItemResponses;
        this.creationDate = order.getCreationDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
