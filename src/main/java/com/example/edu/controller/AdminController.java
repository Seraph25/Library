package com.example.edu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  @GetMapping("/users")
  public String getAllUsers() {
    return "List of all users (only for ADMIN)";
  }

  @GetMapping("/dashboard")
  public String adminDashboard() {
    return "Welcome to the ADMIN dashboard.";
  }
}