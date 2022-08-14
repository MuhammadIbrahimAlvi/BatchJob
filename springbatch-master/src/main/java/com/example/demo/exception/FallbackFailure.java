package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FallbackFailure extends RuntimeException {

    private Integer code;
    private String message;
    private String detailedMessage;

}
