package com.your_next_read.springboot_backend.controller;

import com.your_next_read.springboot_backend.dto.BookSearchResult;
import com.your_next_read.springboot_backend.model.Book;
import com.your_next_read.springboot_backend.repository.BookRepository;
import com.your_next_read.springboot_backend.service.BookImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/tbr")
@RequiredArgsConstructor
public class TbrController {

    private final BookRepository bookRepository;
    private final BookImportService bookImportService;

    @PostMapping
    public Book addToTbr(@RequestBody Book book) {
        Book saved = bookImportService.findOrCreateBook(book);
        saved.setOnTbr(true);
        return bookRepository.save(saved);
    }

    @DeleteMapping("/{id}")
    public void removeFromTbr(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
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