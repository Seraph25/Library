package com.example.edu.controller;

import com.example.edu.entity.Author;
import com.example.edu.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public List<Author> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public Author getById(@PathVariable Long id) {
    return service.getById(id).orElseThrow(() -> new RuntimeException("Author not found"));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Author create(@RequestBody Author author) {
    return service.save(author);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Author update(@PathVariable Long id, @RequestBody Author author) {
    author.setId(id);
    return service.save(author);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }
}