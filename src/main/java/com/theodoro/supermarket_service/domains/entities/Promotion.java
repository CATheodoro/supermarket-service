package com.theodoro.supermarket_service.domains.entities;

import com.theodoro.supermarket_service.api.rest.models.requests.PromotionRequest;
import com.theodoro.supermarket_service.domains.enumerations.PromotionEnum;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "PROMOTION")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @Column(name = "CODE")
    @Enumerated(EnumType.STRING)
    private PromotionEnum code;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "ID_PRODUCT", nullable = false)
    private String idProduct;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "REQUIRED_QUANTITY")
    private Integer requiredQuantity;   //BUY_X_GET_Y_FREE || QTY_BASED_PRICE_OVERRIDE
    @Column(name = "PRICE")
    private Integer price;              //QTY_BASED_PRICE_OVERRIDE
    @Column(name = "AMOUNT")
    private Float amount;               // FLAT_PERCENT
    @Column(name = "FREE_QUANTITY")
    private Integer freeQuantity;       //BUY_X_GET_Y_FREE

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    private ZonedDateTime creationDate;

    public Promotion() {
    }

    public Promotion(PromotionRequest request) {
        this.code = request.getCode();
        this.description = request.getDescription();
        this.idProduct = request.getIdProduct();
        this.active = true;
        this.requiredQuantity = request.getRequiredQuantity();
        this.price = request.getPrice();
        this.amount = request.getAmount();
        this.freeQuantity = request.getFreeQuantity();
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

    public PromotionEnum getCode() {
        return code;
    }

    public void setCode(PromotionEnum code) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
