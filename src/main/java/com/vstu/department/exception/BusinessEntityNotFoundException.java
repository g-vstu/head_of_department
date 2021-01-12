package com.vstu.department.exception;

public class BusinessEntityNotFoundException extends NamedException {

    public BusinessEntityNotFoundException(String message) {
        super(message, BusinessEntityNotFoundException.class);
    }
}
