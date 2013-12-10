package com.acme.rantotta.exception;

public class OrderServiceException extends RuntimeException {

    public OrderServiceException() {
        super();
    }

    public OrderServiceException(String message) {
        super(message);
    }

    
}
