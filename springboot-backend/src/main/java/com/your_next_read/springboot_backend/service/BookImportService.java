package com.your_next_read.springboot_backend.service;

import com.your_next_read.springboot_backend.dto.OpenLibraryEditionsResponse;
import com.your_next_read.springboot_backend.dto.OpenLibrarySearchResponse;
import com.your_next_read.springboot_backend.dto.OpenLibraryWorkResponse;
import com.your_next_read.springboot_backend.model.Book;
import com.your_next_read.springboot_backend.model.Tag;
import com.your_next_read.springboot_backend.repository.BookRepository;
import com.your_next_read.springboot_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookImportService {
    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://openlibrary.org")
            .requestFactory(clientRequestFactory())
            .build();

    private static ClientHttpRequestFactory clientRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return factory;
    }

    public List<Book> importBooksByQuery(String query, int limit) {
        OpenLibrarySearchResponse response = restClient.get()
                .uri("/search.json?q={query}&limit={limit}&fields=key,title,author_name,first_publish_year,number_of_pages_median,subject,cover_i", query, limit)
                .retrieve()
                .body(OpenLibrarySearchResponse.class);

        if (response == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books were found for " + query);

        return response.docs.parallelStream()
                .map(this::mapAndSave)
                .toList();
    }

    private Book mapAndSave(OpenLibrarySearchResponse.Doc doc) {
        Book book = new Book();
        book.setTitle(doc.title);
        book.setAuthor(doc.author_name != null ? doc.author_name.getFirst() : "Unknown");
        book.setPageCount(fetchPageCount(doc.key, doc.number_of_pages_median));
        book.setPublishedYear(doc.first_publish_year);
        book.setCoverImageUrl(doc.cover_i != null
                ? "https://covers.openlibrary.org/b/id/" + doc.cover_i + "-L.jpg"
                : null);
        book.setDescription(fetchDescription(doc.key));

        Set<Tag> tags = new HashSet<>();
        if (doc.subject != null) {
            doc.subject.stream().limit(5).forEach(subjectName -> {
                Tag tag = tagRepository.findByName(subjectName)
                        .orElseGet(() -> tagRepository.save(new Tag(null, subjectName)));
                tags.add(tag);
            });
        }
        book.setTags(tags);

        return bookRepository.save(book);
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

    private String fetchDescription(String workKey) {
        if (workKey == null) return null;

        try {
            OpenLibraryWorkResponse work = restClient.get()
                    .uri(workKey + ".json")
                    .retrieve()
                    .body(OpenLibraryWorkResponse.class);

            if (work == null || work.description == null) return null;

            if (work.description instanceof String s) {
                return s;
            } else if (work.description instanceof Map<?, ?> map) {
                Object value = map.get("value");
                return value != null ? value.toString() : null;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Could not fetch description for " + workKey + ": " + e.getMessage());
            return null;
        }
    }
}