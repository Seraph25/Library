package com.example.edu.controller;

import com.example.edu.entity.Borrowing;
import com.example.edu.service.BorrowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
  private final BorrowingService service;

  public BorrowingController(BorrowingService service) {
    this.service = service;
  }

  @GetMapping("/active")
  public List<Borrowing> getActive() {
    return service.getActiveBorrowings();
  }

  @PostMapping
  public Borrowing borrow(@RequestBody Borrowing b) {
    return service.save(b);
  }

  @PutMapping("/{id}/return")
  public Borrowing returnBook(@PathVariable Long id) {
    return service.returnBook(id).orElse(null);
  }
}