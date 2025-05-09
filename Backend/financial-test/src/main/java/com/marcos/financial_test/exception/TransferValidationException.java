package com.marcos.financial_test.exception;

import org.springframework.http.HttpStatus;

public class TransferValidationException extends RuntimeException {
    private final HttpStatus status;

    public TransferValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
