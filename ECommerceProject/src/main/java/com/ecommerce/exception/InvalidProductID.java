package com.ecommerce.exception;

public class InvalidProductID extends RuntimeException {
    public InvalidProductID(String message) {
        super(message);
    }
}
