package com.example.demo.controller;

import com.example.demo.hashing.SHA256;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController()
public class Controller {
    @PostMapping("/sha256-hash/{accountNumber}")
    public ResponseEntity<String> getBarBySimplePathWithRequestParam(@PathVariable(name = "accountNumber") long accountNumber) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(
                SHA256.getSHA(String.valueOf(accountNumber)),
                HttpStatus.OK);
    }
}
