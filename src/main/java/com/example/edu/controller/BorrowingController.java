package com.example.edu.controller;

import com.example.edu.entity.Borrowing;
import com.example.edu.service.BorrowingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
  private final BorrowingService service;

  public BorrowingController(BorrowingService service) {
    this.service = service;
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<Borrowing> getAll() {
    return service.getAll();
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public Borrowing borrowBook(@RequestBody Borrowing borrowing) {
    return service.save(borrowing);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }
}