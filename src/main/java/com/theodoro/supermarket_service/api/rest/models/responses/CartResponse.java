package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Cart;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "totalPrice",
        "discount",
        "finalPrice",
        "cartItems"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "Cart", collectionRelation = "Carts")
public class CartResponse extends RepresentationModel<CartResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("totalPrice")
    private Integer totalPrice;
    @JsonProperty("discount")
    private Integer discount;
    @JsonProperty("finalPrice")
    private Integer finalPrice;
    @JsonProperty("cartItems")
    private List<CartItemResponse> cartItems;

    public CartResponse(Cart cart, List<CartItemResponse> cartItemResponses) {
        this.id = cart.getId();
        this.totalPrice = cart.getTotalPrice();
        this.discount = cart.getDiscount();
        this.finalPrice = cart.getFinalPrice();
        this.cartItems = cartItemResponses;
    }
}
