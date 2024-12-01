package com.theodoro.supermarket_service.api.rest.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class PromotionRequest {

    @NotBlank(message = "Code is mandatory")
    @JsonProperty("code")
    private String code;
    @NotBlank(message = "Description is mandatory")
    @JsonProperty("description")
    private String description;
    @NotBlank(message = "Product id is mandatory")
    @JsonProperty("idProduct")
    private String idProduct;

    @JsonProperty("requiredQuantity")
    private Integer requiredQuantity;
    @JsonProperty("price")
    private Integer price;

    @JsonProperty("amount")
    private Float amount;

    @JsonProperty("freeQuantity")
    private Integer freeQuantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(Integer freeQuantity) {
        this.freeQuantity = freeQuantity;
    }
}
