package com.theodoro.supermarket_service.infra.handlers;

import com.theodoro.supermarket_service.domains.exceptions.HttpException;
import com.theodoro.supermarket_service.models.errors.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorModel> httpHttpException(HttpException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorModel(ex.getCode(), ex.getMessage()));
    }
}
