package com.theodoro.supermarket_service.api.rest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Product;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@JsonPropertyOrder({
        "id",
        "quantity",
        "unitPrice",
        "Product",
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

    @JsonProperty("product")
    private ProductResponse productResponse;

    @JsonProperty("idCart")
    private String idCart;

    public CartItemResponse(CartItem cartItem) {
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getUnitPrice();
        this.idProduct = cartItem.getIdProduct();
        this.idCart = cartItem.getCart().getId();
    }

    public CartItemResponse(CartItem cartItem, ProductResponse productResponse) {
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getUnitPrice();
        this.productResponse = productResponse;
        this.idCart = cartItem.getCart().getId();
    }
}
