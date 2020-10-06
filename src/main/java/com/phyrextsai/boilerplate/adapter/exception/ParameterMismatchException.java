package com.phyrextsai.boilerplate.adapter.exception;

public class ParameterMismatchException extends RuntimeException {
    private String errorCode = "ERR-20400";

    public ParameterMismatchException() {
        super();
    }

    public ParameterMismatchException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
