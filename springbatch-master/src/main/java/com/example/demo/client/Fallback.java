package com.example.demo.client;

import com.example.demo.exception.FallbackFailure;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Fallback implements HashClient {
    @Override
    public String generateHash(String accountNumber) {
        throw new FallbackFailure(Integer.parseInt(String.valueOf(HttpStatus.NOT_FOUND)), "Internal Server Error", "There is some problem in running the server");
    }
}