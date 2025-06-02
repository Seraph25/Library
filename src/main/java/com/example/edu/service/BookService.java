package com.example.edu.service;

import com.example.edu.entity.Book;
import com.example.edu.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  private final BookRepository repo;

  public BookService(BookRepository repo) {
    this.repo = repo;
  }

  public List<Book> getAll() {
    return repo.findAll();
  }

  public Optional<Book> getById(Long id) {
    return repo.findById(id);
  }

  public Book save(Book book) {
    return repo.save(book);
  }

  public void deleteById(Long id) {
    repo.deleteById(id);
  }

  public Book update(Long id, Book book) {
    book.setId(id);
    return repo.save(book);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}