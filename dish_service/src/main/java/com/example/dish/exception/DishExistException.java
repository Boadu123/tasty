package com.example.dish.exception;

public class DishExistException extends RuntimeException {
    public DishExistException(String message) {
        super(message);
    }
}
