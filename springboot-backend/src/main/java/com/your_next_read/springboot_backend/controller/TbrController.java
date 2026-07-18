package com.your_next_read.springboot_backend.controller;

import com.your_next_read.springboot_backend.model.Book;
import com.your_next_read.springboot_backend.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/tbr")
public class TbrController {

    private final BookRepository bookRepository;

    public TbrController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/{id}")
    public Book addToTbr(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
        book.setOnTbr(true);
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public Book removeFromTbr(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
        book.setOnTbr(false);
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getTbrList() {
        return bookRepository.findByOnTbrTrue();
    }

    @GetMapping("/next-read")
    public Book getNextRead() {
        List<Book> tbr = bookRepository.findByOnTbrTrue();
        if (tbr.isEmpty()) {
            throw new RuntimeException("Your TBR list is empty — add some books first!");
        }
        return tbr.get(new Random().nextInt(tbr.size()));
    }
}