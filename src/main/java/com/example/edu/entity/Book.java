package com.example.edu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private int year;
  private boolean available;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;
}
