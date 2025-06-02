package com.example.edu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Borrowing {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String borrowerName;
  private LocalDate borrowedDate;
  private boolean returned;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;
}
