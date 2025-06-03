package com.example.edu.service;

import com.example.edu.entity.Book;
import com.example.edu.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  public Optional<Book> getById(Long id) {
    return bookRepository.findById(id);
  }

  public Book save(Book book) {
    return bookRepository.save(book);
  }

  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }
}
