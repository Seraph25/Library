package com.example.edu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

  @GetMapping("/profile")
  public String getUserProfile() {
    return "This is the user profile.";
  }

  @GetMapping("/dashboard")
  public String userDashboard() {
    return "Welcome to the USER dashboard.";
  }
}