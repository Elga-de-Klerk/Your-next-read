package com.your_next_read.springboot_backend.controller;

import com.your_next_read.springboot_backend.dto.BookDetails;
import com.your_next_read.springboot_backend.dto.BookSearchResult;
import com.your_next_read.springboot_backend.model.Book;
import com.your_next_read.springboot_backend.repository.BookRepository;
import com.your_next_read.springboot_backend.service.BookImportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookImportService bookImportService;

    public BookController(BookRepository bookRepository, BookImportService bookImportService) {
        this.bookRepository = bookRepository;
        this.bookImportService = bookImportService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query, @RequestParam(defaultValue = "10") int limit) {
        return bookImportService.search(query, limit);
    }

    @GetMapping("/details")
    public BookDetails getDetails(@RequestParam String externalId) {
        return bookImportService.fetchDetails(externalId);
    }
}