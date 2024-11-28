package com.theodoro.supermarket_service.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CartRequest {
    
    @JsonProperty("totalPrice")
    private Integer totalPrice;

    @JsonProperty("items")
    private List<String> items;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
