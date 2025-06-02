package com.example.edu.repository;

import com.example.edu.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
  List<Borrowing> findByReturnedFalse();
}
