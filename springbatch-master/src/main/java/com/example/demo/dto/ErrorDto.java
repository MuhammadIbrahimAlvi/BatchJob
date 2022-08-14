package com.example.demo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ErrorDto {

    private Integer code;
    private String message;
    private String detailedMessage;

}
