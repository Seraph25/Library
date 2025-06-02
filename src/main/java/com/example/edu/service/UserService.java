package com.example.edu.service;

import com.example.edu.dto.AuthRequest;
import com.example.edu.entity.Role;
import com.example.edu.entity.User;
import com.example.edu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repo;
  private final PasswordEncoder encoder;

  public UserService(UserRepository repo, PasswordEncoder encoder) {
    this.repo = repo;
    this.encoder = encoder;
  }

  public User register(AuthRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(encoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    return repo.save(user);
  }
}