package com.ntnn.controller;

import java.util.concurrent.Callable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/hello")
public class GreetingApi {
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<String>> hello() {
        return () -> ResponseEntity.ok("Hello");
    }
}
