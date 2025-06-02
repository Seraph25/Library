package com.example.edu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String country;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Book> books;
}
