package com.example.edu.controller;

import com.example.edu.entity.Book;
import com.example.edu.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
  private final BookService service;

  public BookController(BookService service) {
    this.service = service;
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public List<Book> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public Book getById(@PathVariable Long id) {
    return service.getById(id).orElseThrow(() -> new RuntimeException("Book not found"));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Book create(@RequestBody Book book) {
    return service.save(book);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Book update(@PathVariable Long id, @RequestBody Book book) {
    book.setId(id);
    return service.save(book);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }
}