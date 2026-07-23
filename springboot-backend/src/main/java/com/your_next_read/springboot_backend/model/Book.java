package com.your_next_read.springboot_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String externalId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 2000)
    private String description;

    private Integer pageCount;
    private Integer publishedYear;
    private String coverImageUrl;

    @Column(nullable = false)
    private boolean onTbr = false;

    @ElementCollection
    @CollectionTable(name = "book_tags", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "tag_name")
    private Set<String> tags = new HashSet<>();
}