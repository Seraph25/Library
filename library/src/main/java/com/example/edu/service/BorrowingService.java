package com.example.edu.service;

import com.example.edu.entity.Borrowing;
import com.example.edu.repository.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {
  private final BorrowingRepository repo;

  public BorrowingService(BorrowingRepository repo) {
    this.repo = repo;
  }

  public List<Borrowing> getActiveBorrowings() {
    return repo.findByReturnedFalse();
  }

  public Borrowing save(Borrowing b) {
    return repo.save(b);
  }

  public Optional<Borrowing> returnBook(Long id) {
    Optional<Borrowing> optional = repo.findById(id);
    optional.ifPresent(b -> {
      b.setReturned(true);
      repo.save(b);
    });
    return optional;
  }
}