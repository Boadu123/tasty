package com.example.auth_service.exception;

import com.example.auth_service.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResource(DuplicateResourceException ex) {
        ApiError error = new ApiError();
        error.setStatus("fail");
        error.setCode(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setError(null);  // Optional: you can add more details if needed

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ApiError.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> {
                    ApiError.FieldError fieldError = new ApiError.FieldError();
                    fieldError.setField(err.getField());
                    fieldError.setMessage(err.getDefaultMessage());
                    return fieldError;
                }).toList();

        ApiError error = new ApiError();
        error.setStatus("fail");
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Validation error");
        error.setError(fieldErrors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError error = new ApiError();
        error.setStatus("error");
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Something went wrong");
        error.setError(null);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

