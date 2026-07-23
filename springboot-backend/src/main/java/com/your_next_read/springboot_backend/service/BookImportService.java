package com.your_next_read.springboot_backend.service;

import com.your_next_read.springboot_backend.dto.*;
import com.your_next_read.springboot_backend.model.Book;
import com.your_next_read.springboot_backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookImportService {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://openlibrary.org")
            .requestFactory(clientRequestFactory())
            .build();

    private final BookRepository bookRepository;

    private static ClientHttpRequestFactory clientRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return factory;
    }

    public List<Book> search(String query, int limit) {
        OpenLibrarySearchResponse response = restClient.get()
                .uri("/search.json?q={query}&limit={limit}&fields=key,title,author_name,first_publish_year,number_of_pages_median,subject,cover_i", query, limit)
                .retrieve()
                .body(OpenLibrarySearchResponse.class);

        return response.docs.stream().map(this::mapToBook).toList();
    }

    private Book mapToBook(OpenLibrarySearchResponse.Doc doc) {
        Optional<Book> existing = bookRepository.findByExternalId(doc.key);
        if (existing.isPresent()) {
            return existing.get();
        }

        Book book = new Book();
        book.setExternalId(doc.key);
        book.setTitle(doc.title);
        book.setAuthor(doc.author_name != null ? doc.author_name.get(0) : "Unknown");
        book.setPublishedYear(doc.first_publish_year);
        book.setPageCount(doc.number_of_pages_median);
        book.setCoverImageUrl(doc.cover_i != null
                ? "https://covers.openlibrary.org/b/id/" + doc.cover_i + "-L.jpg"
                : null);
        book.setTags(doc.subject != null
                ? new HashSet<>(doc.subject.stream().limit(5).toList())
                : new HashSet<>());
        return book;
    }

    public Book findOrCreateBook(Book input) {
        if (input.getExternalId() != null) {
            Optional<Book> existing = bookRepository.findByExternalId(input.getExternalId());
            if (existing.isPresent()) return existing.get();
        }
        input.setId(null);
        return bookRepository.save(input);
    }

    public BookDetails fetchDetails(String externalId) {
        BookDetails details = new BookDetails();
        details.description = fetchDescription(externalId);
        details.pageCount = fetchPageCount(externalId, null);
        return details;
    }

    private String fetchDescription(String workKey) {
        if (workKey == null) return null;
        try {
            OpenLibraryWorkResponse work = restClient.get()
                    .uri(workKey + ".json")
                    .retrieve()
                    .body(OpenLibraryWorkResponse.class);
            if (work == null || work.description == null) return null;
            if (work.description instanceof String s) return s;
            if (work.description instanceof Map<?, ?> map) {
                Object value = map.get("value");
                return value != null ? value.toString() : null;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Could not fetch description for " + workKey + ": " + e.getMessage());
            return null;
        }
    }

    private Integer fetchPageCount(String workKey, Integer fallback) {
        if (workKey == null) return fallback;
        try {
            OpenLibraryEditionsResponse editions = restClient.get()
                    .uri(workKey + "/editions.json")
                    .retrieve()
                    .body(OpenLibraryEditionsResponse.class);
            if (editions == null || editions.entries == null) return fallback;
            return editions.entries.stream()
                    .map(e -> e.number_of_pages)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(fallback);
        } catch (Exception e) {
            System.out.println("Could not fetch page count for " + workKey + ": " + e.getMessage());
            return fallback;
        }
    }
}