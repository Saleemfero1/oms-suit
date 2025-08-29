package com.oms.exception;

import com.oms.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<BaseResponse<Void>> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(BaseResponse.<Void>builder()
                        .status(ex.getStatusCode())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(Exception.class) // fallback for unknown errors
    public ResponseEntity<BaseResponse<Void>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(BaseResponse.<Void>builder()
                        .status(500)
                        .message("Internal server error: " + ex.getMessage())
                        .build()
                );
    }
}

