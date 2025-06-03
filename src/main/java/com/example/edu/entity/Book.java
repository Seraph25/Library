package com.example.edu.entity;

import jakarta.persistence.*;

@Entity
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

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public int getYear() { return year; }
  public void setYear(int year) { this.year = year; }

  public boolean isAvailable() { return available; }
  public void setAvailable(boolean available) { this.available = available; }

  public Author getAuthor() { return author; }
  public void setAuthor(Author author) { this.author = author; }
}