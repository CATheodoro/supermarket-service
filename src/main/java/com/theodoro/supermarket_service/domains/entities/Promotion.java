package com.theodoro.supermarket_service.domains.entities;

import com.theodoro.supermarket_service.api.rest.models.requests.PromotionRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "PROMOTION")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @Column(name = "CODE", nullable = false)
    private String code;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "PRODUCT", nullable = false)
    private String idProduct;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "REQUIRED_QUANTITY")
    private Integer requiredQuantity;   //BUY_X_GET_Y_FREE || QTY_BASED_PRICE_OVERRIDE
    @Column(name = "PRICE")
    private Integer price;              //QTY_BASED_PRICE_OVERRIDE
    @Column(name = "AMOUNT")
    private Float amount;             // FLAT_PERCENT
    @Column(name = "FREE_QUANTITY")
    private Integer freeQuantity;       //BUY_X_GET_Y_FREE

    public Promotion() {
    }

    public Promotion(PromotionRequest request) {
        this.code = request.getCode();
        this.description = request.getDescription();
        this.idProduct = request.getIdProduct();
        this.active = request.getActive();
        this.requiredQuantity = request.getRequiredQuantity();
        this.price = request.getPrice();
        this.amount = request.getAmount();
        this.freeQuantity = request.getFreeQuantity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
