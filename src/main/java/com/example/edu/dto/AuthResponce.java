package com.example.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponce {
  private String token;
  private String message;
}