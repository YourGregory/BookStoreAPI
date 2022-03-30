package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.security.JwtAuthenticationResponse;
import com.example.bookstoreapi.dto.security.LoginRequest;
import com.example.bookstoreapi.dto.security.SignUpRequest;
import com.example.bookstoreapi.response.ApiResponse;
import com.example.bookstoreapi.service.AuthService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest request) {
        ApiResponse apiResponse;
        if (authService.existsByEmail(request.getEmail())) {
            apiResponse = ApiResponse.createEmailAlreadyTakenResponse();
        } else if (authService.existsByUsername(request.getName())) {
            apiResponse = ApiResponse.createUsernameAlreadyTakenResponse();
        } else {
            apiResponse = ApiResponse.createSuccessfulRegisterResponse();
            authService.registerUser(request);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticateUser(request));
    }
}