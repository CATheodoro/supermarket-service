package com.theodoro.supermarket_service.domains.exceptions;

import com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum;
import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

  public BadRequestException(final String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(final ExceptionMessagesEnum exceptionMessagesEnum) {
    super(exceptionMessagesEnum.getCode(), HttpStatus.BAD_REQUEST, exceptionMessagesEnum.getMessage());
  }

  public BadRequestException(final ExceptionMessagesEnum exceptionMessagesEnum, Object... args) {
    super(exceptionMessagesEnum.getCode(), HttpStatus.BAD_REQUEST, String.format(exceptionMessagesEnum.getMessage(), args));
  }
}