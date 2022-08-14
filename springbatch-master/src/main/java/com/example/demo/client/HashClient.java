package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "hash-account-service", url = "http://localhost:8033/", fallback = Fallback.class)
public interface HashClient {
    @RequestMapping(method = RequestMethod.GET, value = "/sha256-hash/{accountNumber}", consumes = "application/json")
    String generateHash(@PathVariable("accountNumber") String accountNumber);
}
