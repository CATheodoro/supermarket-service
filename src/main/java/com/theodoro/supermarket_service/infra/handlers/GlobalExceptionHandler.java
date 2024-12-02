package com.theodoro.supermarket_service.infra.handlers;

import com.theodoro.supermarket_service.api.rest.models.errors.ErrorModel;
import com.theodoro.supermarket_service.domains.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception ex) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ErrorModel(INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorModel> httpHttpException(HttpException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorModel(ex.getCode(), ex.getMessage()));
    }
}
