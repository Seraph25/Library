package com.example.edu.controller;

import com.example.edu.entity.Author;
import com.example.edu.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
  private final AuthorService service;

  public AuthorController(AuthorService service) {
    this.service = service;
  }

  @GetMapping
  public List<Author> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Author getById(@PathVariable Long id) {
    return service.getById(id).orElse(null);
  }

  @PostMapping
  public Author add(@RequestBody Author author) {
    return service.save(author);
  }
}