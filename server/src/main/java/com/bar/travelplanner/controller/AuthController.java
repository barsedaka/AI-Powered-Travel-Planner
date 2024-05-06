package com.bar.travelplanner.controller;

import com.bar.travelplanner.dto.JwtAuthResponse;
import com.bar.travelplanner.dto.LoginDTO;
import com.bar.travelplanner.dto.RegisterDTO;
import com.bar.travelplanner.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) throws Exception {
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
