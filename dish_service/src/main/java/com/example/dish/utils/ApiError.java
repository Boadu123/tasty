package com.example.dish.utils;

import java.util.List;

public class ApiError {
    private String status;
    private int code;
    private String message;
    private List<FieldError> error;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getError() {
        return error;
    }

    public void setError(List<FieldError> error) {
        this.error = error;
    }

    // Nested static class
    public static class FieldError {
        private String field;
        private String message;

        // Getters and Setters
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
