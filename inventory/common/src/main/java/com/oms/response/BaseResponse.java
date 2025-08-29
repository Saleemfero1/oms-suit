package com.oms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    @Builder.Default
    private Instant timestamp = Instant.now();
    private int status;
    private String message;
    private T data;
}