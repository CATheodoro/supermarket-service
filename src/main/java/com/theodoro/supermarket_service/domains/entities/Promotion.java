package com.theodoro.supermarket_service.domains.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PROMOTION")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REQUIRED_QUANTITY")
    private Integer requiredQuantity; //BUY_X_GET_Y_FREE || QTY_BASED_PRICE_OVERRIDE
    @Column(name = "PRICE")
    private Integer price; //QTY_BASED_PRICE_OVERRIDE

    @Column(name = "AMOUNT")
    private Integer amount; // FLAT_PERCENT em porcentagem

    @Column(name = "FREE_QUANTITY")
    private Integer freeQuantity; //BUY_X_GET_Y_FREE
}
