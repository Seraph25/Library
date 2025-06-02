package com.example.edu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequest {

  @NotBlank(message = "Title is required")
  @Size(max = 255, message = "Title must be less than 255 characters")
  private String title;

  @NotBlank(message = "Author name is required")
  private String author;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}