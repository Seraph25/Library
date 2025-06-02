package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Author {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String country;

  @OneToMany(mappedBy = "author")
  private List<Book> books;
}