package com.example.edu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
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

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getBorrowerName() { return borrowerName; }
  public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }

  public LocalDate getBorrowedDate() { return borrowedDate; }
  public void setBorrowedDate(LocalDate borrowedDate) { this.borrowedDate = borrowedDate; }

  public boolean isReturned() { return returned; }
  public void setReturned(boolean returned) { this.returned = returned; }

  public Book getBook() { return book; }
  public void setBook(Book book) { this.book = book; }
}
