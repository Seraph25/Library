package com.example.edu.service;

import com.example.edu.entity.Author;
import com.example.edu.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
  private final AuthorRepository repo;

  public AuthorService(AuthorRepository repo) {
    this.repo = repo;
  }

  public List<Author> getAll() {
    return repo.findAll();
  }

  public Optional<Author> getById(Long id) {
    return repo.findById(id);
  }

  public Author save(Author author) {
    return repo.save(author);
  }
}