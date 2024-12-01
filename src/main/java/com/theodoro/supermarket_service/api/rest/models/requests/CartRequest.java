package com.theodoro.supermarket_service.api.rest.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theodoro.supermarket_service.domains.entities.CartItem;

import java.util.List;

public class CartRequest {
    
    @JsonProperty("totalPrice")
    private Integer totalPrice;

    @JsonProperty("items")
    private List<CartItem> items;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
