package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Order;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "totalPrice",
        "cartItems"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "Order", collectionRelation = "Orders")
public class OrderResponse extends RepresentationModel<OrderResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("totalPrice")
    private Integer totalPrice;
    @JsonProperty("cartItems")
    private List<OrderItemResponse> orderItems;

    public OrderResponse(Order order, List<OrderItemResponse> orderItemResponses) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.orderItems = orderItemResponses;
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
}
