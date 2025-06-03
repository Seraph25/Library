package com.example.edu.service;

import com.example.edu.entity.Author;
import com.example.edu.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  public Optional<Author> getById(Long id) {
    return authorRepository.findById(id);
  }

  public Author save(Author author) {
    return authorRepository.save(author);
  }

  public void deleteById(Long id) {
    authorRepository.deleteById(id);
  }
}
