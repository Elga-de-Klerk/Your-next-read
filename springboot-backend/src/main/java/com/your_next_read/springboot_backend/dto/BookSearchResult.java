package com.your_next_read.springboot_backend.dto;

import java.util.List;

public class BookSearchResult {
    public String externalId;
    public String title;
    public String author;
    public String description;
    public Integer pageCount;
    public Integer publishedYear;
    public String coverImageUrl;
    public List<String> tags;
}