package com.theodoro.supermarket_service.domains.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CART_ITEM")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "UNIT_PRICE")
    private Integer unitPrice;

    @Column(name = "ID_PRODUCT")
    private String idProduct;
    @Column(name = "ID_CART")
    private String idCart;

    public CartItem() {
    }

    public CartItem(Integer cartItemsQuantity, Cart cart, Product product) {
        this.quantity = cartItemsQuantity;
        this.unitPrice = product.getPrice();
        this.idProduct = product.getId();
        this.idCart = cart.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }
}
