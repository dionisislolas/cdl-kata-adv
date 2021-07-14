package com.example.cdladv.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(long id) {
        super("Order with id=" + id + " not found");
    }
}
