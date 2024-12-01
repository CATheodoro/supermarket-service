package com.theodoro.supermarket_service.api.rest.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CartItemRequest {
    @NotBlank(message = "Id cart is mandatory")
    @JsonProperty("idCart")
    private String idCart;

    @NotBlank(message = "Id product is mandatory")
    @JsonProperty("idProduct")
    private String idProduct;

    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
