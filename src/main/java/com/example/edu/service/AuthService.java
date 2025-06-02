package com.example.edu.service;

import com.example.edu.entity.Role;
import com.example.edu.entity.User;
import com.example.edu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("User already exists");
    }

    User user = User.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .role(Role.USER)
        .build();

    return userRepository.save(user);
  }

  public User registerAdmin(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Admin already exists");
    }

    User admin = User.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .role(Role.ADMIN)
        .build();

    return userRepository.save(admin);
  }
}