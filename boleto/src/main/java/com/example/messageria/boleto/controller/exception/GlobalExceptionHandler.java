package com.example.messageria.boleto.controller.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<Object> applicationException(ApplicationException e, WebRequest request) {
    var response = ErroResponse.builder()
        .erro(e.getMessage())
        .codigo(HttpStatus.BAD_REQUEST.value())
        .timestamp(new Date())
        .path(request.getDescription(false)).build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
