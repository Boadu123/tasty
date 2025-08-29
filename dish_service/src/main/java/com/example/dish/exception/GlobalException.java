package com.example.dish.exception;

import com.example.dish.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    // Handle IllegalArgumentException (bad request input)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus("error");
        apiError.setCode(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage(ex.getMessage());
        apiError.setError(null);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Handle Duplicate Menu Exception
    @ExceptionHandler(MenuExistException.class)
    public ResponseEntity<ApiError> handleMenuExistException(MenuExistException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus("error");
        apiError.setCode(HttpStatus.CONFLICT.value());
        apiError.setMessage(ex.getMessage());
        apiError.setError(null);

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // Handle Bean Validation Errors (@Valid DTO validation failures)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiError.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> {
                    ApiError.FieldError fieldError = new ApiError.FieldError();
                    fieldError.setField(err.getField());
                    fieldError.setMessage(err.getDefaultMessage());
                    return fieldError;
                })
                .collect(Collectors.toList());

        ApiError apiError = new ApiError();
        apiError.setStatus("error");
        apiError.setCode(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage("Validation failed");
        apiError.setError(fieldErrors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Catch-all for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ex.printStackTrace();
        ApiError apiError = new ApiError();
        apiError.setStatus("error");
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setMessage("An unexpected error occurred: " + ex.getMessage());
        apiError.setError(null);

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
