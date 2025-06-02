package com.example.edu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthorRequest {

  @NotBlank(message = "Author name is required")
  @Size(max = 100, message = "Author name must be less than 100 characters")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}