package com.example.edu.controller;

import com.example.edu.dto.AuthRequest;
import com.example.edu.dto.AuthResponce;
import com.example.edu.entity.User;
import com.example.edu.security.JwtUtil;
import com.example.edu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody AuthRequest request) {
    User user = authService.register(request.getUsername(), request.getPassword());
    return ResponseEntity.ok(user);
  }

  @PostMapping("/register-admin")
  public ResponseEntity<User> registerAdmin(@RequestBody AuthRequest request) {
    User user = authService.registerAdmin(request.getUsername(), request.getPassword());
    return ResponseEntity.ok(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );

      String token = jwtUtil.generateToken(request.getUsername());

      AuthResponce response = new AuthResponce(token, "Login successful");
      return ResponseEntity.ok(response);

    } catch (AuthenticationException e) {
      return ResponseEntity
          .status(401)
          .body(new AuthResponce(null, "Invalid username or password"));
    }
  }
}