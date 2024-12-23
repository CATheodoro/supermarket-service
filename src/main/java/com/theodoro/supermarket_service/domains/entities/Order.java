package com.theodoro.supermarket_service.domains.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;
    @Column(name = "DISCOUNT", nullable = false)
    private Integer discount;
    @Column(name = "FINAL_PRICE", nullable = false)
    private Integer finalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    private ZonedDateTime creationDate;

    public Order() {
    }

    public Order(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.discount = cart.getDiscount();
        this.finalPrice = cart.getFinalPrice();
    }

    @PrePersist
    private void prePersists() {
        this.creationDate = ZonedDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }
}
