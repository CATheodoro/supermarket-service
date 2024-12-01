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

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Cart cart, Product product, Integer cartItemsQuantity) {
        this.cart = cart;
        this.idProduct = product.getId();
        this.unitPrice = product.getPrice();
        this.quantity = cartItemsQuantity;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
