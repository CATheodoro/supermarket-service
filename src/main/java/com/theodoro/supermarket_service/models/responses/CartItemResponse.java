package com.theodoro.supermarket_service.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@JsonPropertyOrder({
        "id",
        "quantity",
        "unitPrice",
        "idProduct",
        "idCart"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "CartItem", collectionRelation = "CartItems")
public class CartItemResponse extends RepresentationModel<CartItemResponse> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("unitPrice")
    private Integer unitPrice;

    @JsonProperty("idProduct")
    private String idProduct;
    @JsonProperty("idCart")
    private String idCart;

    public CartItemResponse(CartItem cartItem) {
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getUnitPrice();
        this.idProduct = cartItem.getIdProduct();
        this.idCart = cartItem.getIdCart();
    }

    public CartItemResponse(String idProduct, String idCart, Integer quantity, Integer unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.idProduct = idProduct;
        this.idCart = idCart;
    }
}
