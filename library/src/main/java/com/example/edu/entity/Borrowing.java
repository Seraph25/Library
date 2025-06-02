package com.example.edu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Borrowing {
  @Id
  @GeneratedValue
  private Long id;

  private String borrowerName;
  private LocalDate borrowedDate;
  private boolean returned;

  @ManyToOne
  private Book book;
}
