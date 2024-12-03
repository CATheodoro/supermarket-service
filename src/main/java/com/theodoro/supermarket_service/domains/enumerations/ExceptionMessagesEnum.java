package com.theodoro.supermarket_service.domains.enumerations;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ExceptionMessagesEnum {

    //200
    CART_CLEAR_SUCCESSFUL(200001, OK, "Clear cart successfully."),

    //400
    PRODUCT_NOT_ENOUGH_STOCK_BAD_REQUEST(400001, BAD_REQUEST, "Product not enough stock."),

    //404
    PRODUCT_ID_NOT_FOUND(404001, NOT_FOUND, "Product not found for id informed."),
    CART_ID_NOT_FOUND(404002, NOT_FOUND, "Cart not found for id informed."),
    CART_ITEM_ID_NOT_FOUND(404003, NOT_FOUND, "Cart item not found for id informed."),
    ORDER_ID_NOT_FOUND(404004, NOT_FOUND, "Order not found for id informed."),
    ORDER_ITEM_ID_NOT_FOUND(404005, NOT_FOUND, "Order item not found for id informed."),
    PROMOTION_ID_NOT_FOUND(404006, NOT_FOUND, "Promotion not found for id informed.");


    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    ExceptionMessagesEnum(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
