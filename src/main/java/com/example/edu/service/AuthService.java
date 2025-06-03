package com.example.edu.service;

import com.example.edu.entity.User;
import com.example.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User register(String username, String password) {
    if (userRepository.existsByUsername(username)) {
      throw new RuntimeException("User already exists");
    }

    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setRole("USER");

    return userRepository.save(user);
  }

  public User registerAdmin(String username, String password) {
    if (userRepository.existsByUsername(username)) {
      throw new RuntimeException("Admin already exists");
    }

    User admin = new User();
    admin.setUsername(username);
    admin.setPassword(passwordEncoder.encode(password));
    admin.setRole("ADMIN");

    return userRepository.save(admin);
  }

  public User login(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }

    return user;
  }
}
