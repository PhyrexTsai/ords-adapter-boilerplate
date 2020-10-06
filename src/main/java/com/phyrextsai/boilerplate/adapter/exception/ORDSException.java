package com.phyrextsai.boilerplate.adapter.exception;

import org.springframework.http.HttpStatus;

public class ORDSException extends RuntimeException {
  
  public ORDSException() {
    super();
  }

  public ORDSException(String message) {
    super(message);
  }

  public ORDSException(String message, HttpStatus statusCode) {
    super("Error with Status Code: " + statusCode.value() + ", message: " + message);
  }

}
