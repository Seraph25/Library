package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private int year;
  private boolean available;

  @ManyToOne
  private Author author;
}
