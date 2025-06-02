package com.example.edu.controller;

import com.example.edu.dto.AuthRequest;
import com.example.edu.entity.Role;
import com.example.edu.entity.User;
import com.example.edu.repository.UserRepository;
import com.example.edu.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody AuthRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("User already exists");
    }

    User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/register-admin")
  public ResponseEntity<String> registerAdmin(@RequestBody AuthRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("Admin already exists");
    }

    User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ADMIN)
        .build();

    userRepository.save(user);
    return ResponseEntity.ok("Admin registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    String token = tokenService.generateToken(authentication.getName());
    return ResponseEntity.ok(token);
  }
}