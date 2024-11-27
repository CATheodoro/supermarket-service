package com.theodoro.supermarket_service.domains.exceptions;

import com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    public NotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(final ExceptionMessagesEnum exceptionMessagesEnum) {
        super(exceptionMessagesEnum.getCode(), HttpStatus.NOT_FOUND, exceptionMessagesEnum.getMessage());
    }
}
