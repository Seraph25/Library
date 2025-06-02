package com.example.edu.controller;

import com.example.edu.entity.Book;
import com.example.edu.service.BookService;
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
  public List<Book> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Book getById(@PathVariable Long id) {
    return service.getById(id).orElse(null);
  }

  @PostMapping
  public Book add(@RequestBody Book book) {
    return service.save(book);
  }

  @PutMapping("/{id}")
  public Book update(@PathVariable Long id, @RequestBody Book book) {
    return service.update(id, book);
  }
}