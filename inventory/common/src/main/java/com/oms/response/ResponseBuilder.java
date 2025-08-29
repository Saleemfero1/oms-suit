package com.oms.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<BaseResponse<T>> success(T data, String message) {
        BaseResponse<T> baseResponse =  BaseResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.ok(baseResponse);
    }

    public static <T> ResponseEntity<BaseResponse<T>> error(String message, int status) {
        BaseResponse<T> baseResponse =  BaseResponse.<T>builder()
                .status(200)
                .message(message)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatusCode.valueOf(status));
    }
}
