package com.example.dish.exception;

public class MenuExistException extends RuntimeException {
    public MenuExistException(String message) {
        super(message);
    }
}
