package com.example.simple;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SimpleController {
    @GetMapping
    public ResponseEntity<SimpleApplication.Message> index() {
        return ResponseEntity.ok(new SimpleApplication.Message("hello!"));
    }
}
