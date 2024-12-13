package com.example.messageria.boleto.controller.exception;

public class CustomizedNotFoundException extends RuntimeException {

  public CustomizedNotFoundException(String msg) {
    super(msg);
  }
}
