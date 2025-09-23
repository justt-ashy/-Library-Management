package com.example.LMS_Backend.controller;

import com.example.LMS_Backend.model.User;
import com.example.LMS_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.example.LMS_Backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User created = userService.signup(user);
        return ResponseEntity.ok(created);
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    public static class LoginResponse {
        public String token;
        public LoginResponse(String token) { this.token = token; }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username, request.password)
            );
            String token = jwtUtil.generateToken(auth.getName(), java.util.Map.of());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).build();
        }
    }
}


