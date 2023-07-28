package com.dans.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dans.api.dto.AuthRequest;
import com.dans.api.dto.ResponseModel;
import com.dans.api.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        ResponseModel response = authService.login(authRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        ResponseModel response = authService.register(authRequest);
        return ResponseEntity.ok(response);
    }
}
