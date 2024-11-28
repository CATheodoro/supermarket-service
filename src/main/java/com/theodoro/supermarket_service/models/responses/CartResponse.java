package com.theodoro.supermarket_service.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Cart;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "totalPrice"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "Cart", collectionRelation = "Carts")
public class CartResponse  extends RepresentationModel<CartResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("totalPrice")
    private Integer totalPrice;
    @JsonProperty("totalPrice")
    private List<String> cartItems;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.totalPrice = cart.getTotalPrice();
        this.cartItems = cart.getItems();
    }
}
