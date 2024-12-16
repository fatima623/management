package com.todo.todo.management.project.controller;

import com.todo.todo.management.project.dto.RegisterDto;
import com.todo.todo.management.project.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String>Register(@RequestBody RegisterDto registerDto) {
        return new  ResponseEntity(authService.Register(registerDto), HttpStatus.CREATED);
    }
}
