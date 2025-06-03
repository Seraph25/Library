package com.example.edu.service;

import com.example.edu.entity.Borrowing;
import com.example.edu.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {
  @Autowired
  private BorrowingRepository borrowingRepository;

  public List<Borrowing> getAll() {
    return borrowingRepository.findAll();
  }

  public List<Borrowing> getActiveBorrowings() {
    return borrowingRepository.findByReturnedFalse();
  }

  public Borrowing save(Borrowing borrowing) {
    borrowing.setReturned(false);
    if (borrowing.getBook() != null) {
      borrowing.getBook().setAvailable(false);
    }
    return borrowingRepository.save(borrowing);
  }

  public void deleteById(Long id) {
    borrowingRepository.deleteById(id);
  }

  public Optional<Borrowing> returnBook(Long id) {
    Optional<Borrowing> optional = borrowingRepository.findById(id);
    optional.ifPresent(b -> {
      b.setReturned(true);
      if (b.getBook() != null) {
        b.getBook().setAvailable(true);
      }
      borrowingRepository.save(b);
    });
    return optional;
  }
}
