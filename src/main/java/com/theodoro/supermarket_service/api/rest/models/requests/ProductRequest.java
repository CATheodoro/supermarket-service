package com.theodoro.supermarket_service.api.rest.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public class ProductRequest {

    @NotBlank(message = "Product name is mandatory")
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("price")
    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
