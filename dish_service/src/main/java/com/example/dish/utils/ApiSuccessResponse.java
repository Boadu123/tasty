package com.example.dish.utils;

import org.springframework.http.HttpStatus;

public class ApiSuccessResponse {

    public static <T> ApiResponse<T> buildSuccessResponse(HttpStatus status, String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();

        apiResponse.setStatus("success");
        apiResponse.setCode(status.value());
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildSuccessResponse(HttpStatus status, String message) {
        return buildSuccessResponse(status, message, null);
    }
}
