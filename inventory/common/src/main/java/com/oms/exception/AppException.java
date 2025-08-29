package com.oms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class AppException extends RuntimeException {
    private final int statusCode;
    public AppException(String message, int code) {
        super(message);
        this.statusCode = code;
    }
}
