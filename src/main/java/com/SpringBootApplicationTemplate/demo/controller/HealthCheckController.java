package com.SpringBootApplicationTemplate.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check")
@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hey hey");
    }
}
