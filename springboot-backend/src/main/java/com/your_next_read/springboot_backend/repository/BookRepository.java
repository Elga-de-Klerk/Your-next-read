package com.your_next_read.springboot_backend.repository;

import com.your_next_read.springboot_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOnTbrTrue();
    Optional<Book> findByExternalId(String externalId);
}
